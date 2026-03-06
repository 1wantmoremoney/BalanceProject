package com.balanceproject.service;

import com.balanceproject.dto.TransactionItem;
import com.balanceproject.dto.TransactionResult;
import com.balanceproject.entity.AccountBalance;
import com.balanceproject.entity.BalanceTransaction;
import com.balanceproject.repository.AccountBalanceRepo;
import com.balanceproject.repository.BalanceTractionRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Description:
 * Author: Jiangchangpeng
 * Date: 2026/03/06/11:49
 */
@Component
@AllArgsConstructor
@Slf4j
public class TransactionExecutor {
    private final AccountBalanceRepo accountBalanceRepo;
    private final BalanceTractionRepo balanceTractionRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected TransactionResult doTransaction(TransactionItem transaction, Boolean checkBalance) {
        int maxRetries = 3;
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                return executeOnce(transaction, checkBalance);
            } catch (ObjectOptimisticLockingFailureException e) {
                if (attempt == maxRetries - 1) {
                    log.warn("账户 {} 乐观锁重试 {} 次后失败", transaction.getAccountId(), maxRetries);
                    return TransactionResult.failure(transaction.getAccountId(), transaction.getAmount(), "并发冲突，请稍后重试");
                }
                log.debug("账户 {} 乐观锁冲突，第 {} 次重试", transaction.getAccountId(), attempt + 1);
            }
        }
        return TransactionResult.failure(transaction.getAccountId(), transaction.getAmount(), "并发冲突");
    }

    private TransactionResult executeOnce(TransactionItem transaction, Boolean checkBalance) {
        String accountId = transaction.getAccountId();
        BigDecimal amount = transaction.getAmount();
        AccountBalance accountBalance = accountBalanceRepo.getBalanceByAccountId(accountId);
        BigDecimal newBalance = accountBalance.getBalance().add(amount);
        if (checkBalance && newBalance.compareTo(BigDecimal.ZERO) < 0) {
            return TransactionResult.failure(accountId, amount, "余额小于0");
        }
        accountBalance.setBalance(newBalance);
        accountBalanceRepo.save(accountBalance);
        BalanceTransaction tx = new BalanceTransaction();
        tx.setAccountId(accountId);
        tx.setAmount(amount);
        tx.setEndingBalance(newBalance);
        BalanceTransaction saved = balanceTractionRepo.save(tx);
        log.info("账户 {} 交易成功，amount={}，endingBalance={}", accountId, amount, newBalance);
        return TransactionResult.success(accountId, amount, saved.getId(), newBalance);
    }





}

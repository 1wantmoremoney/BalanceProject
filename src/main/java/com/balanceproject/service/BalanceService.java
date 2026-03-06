package com.balanceproject.service;

import com.balanceproject.dto.TransactionItem;
import com.balanceproject.dto.TransactionResult;
import com.balanceproject.dto.request.IssueTractionRequest;
import com.balanceproject.dto.response.BalanceResponse;
import com.balanceproject.dto.response.IssueTransactionsResponse;
import com.balanceproject.entity.Account;
import com.balanceproject.entity.AccountBalance;
import com.balanceproject.repository.AccountBalanceRepo;
import com.balanceproject.repository.AccountRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description: 交易服务类
 * Author: Jiangchangpeng
 * Date: 2026/03/05/15:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {
    private final AccountBalanceRepo accountBalanceRepo;
    private final AccountRepo accountRepo;
    private final TransactionExecutor transactionExecutor;



    public IssueTransactionsResponse issueTransaction(@Valid IssueTractionRequest request) {
        Boolean checkBalance = request.getCheckBalance();
        List<TransactionItem> tractionItems = request.getItems();
        ArrayList<TransactionResult> results = new ArrayList<>();

        filterAccountNotExist(tractionItems, results);

        List<TransactionResult> transactionResults = tractionItems.stream().map(transaction ->
             transactionExecutor.doTransaction(transaction, checkBalance)
        ).toList();
        
        results.addAll(transactionResults);
        return new IssueTransactionsResponse(results);
    }



    public BalanceResponse getBalance(String accountId) {
        AccountBalance accountBalance = accountBalanceRepo.getBalanceByAccountId(accountId);
        if (!Objects.isNull(accountBalance)) {
            return new BalanceResponse(accountId, accountBalance.getBalance());
        }
        return null;
    }


    private void filterAccountNotExist(List<TransactionItem> transactionItems, ArrayList<TransactionResult> results) {
        if (transactionItems == null || transactionItems.isEmpty()) {
            return;
        }
        List<String> accountIds = transactionItems.stream().map(TransactionItem::getAccountId).toList();
        List<String> existAccount = accountRepo.findByAccountIdIn(accountIds).stream().map(Account::getAccountId).toList();
        Set<String> notExistAccountIds = new HashSet<>(accountIds);
        notExistAccountIds.removeAll(new HashSet<>(existAccount));

        if (!notExistAccountIds.isEmpty()) {
            List<TransactionResult> accountNotExist = notExistAccountIds.stream().map(id ->
                TransactionResult.failure(id, null, "账户不存在")
            ).toList();
            results.addAll(accountNotExist);

            transactionItems.removeIf(item -> notExistAccountIds.contains(item.getAccountId()));
        }
    }
}

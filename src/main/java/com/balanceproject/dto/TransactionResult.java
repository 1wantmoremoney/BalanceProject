package com.balanceproject.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 单比交易结果
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:08
 */
@Data
public class TransactionResult {

    private String accountId;
    private BigDecimal amount;
    private Long transactionId;
    private BigDecimal endingBalance;
    private boolean success;
    private String reason;

    public static TransactionResult success(String accountId, BigDecimal amount,
                                            Long transactionId, BigDecimal endingBalance) {
        TransactionResult r = new TransactionResult();
        r.accountId = accountId;
        r.amount = amount;
        r.transactionId = transactionId;
        r.endingBalance = endingBalance;
        r.success = true;
        return r;
    }

    public static TransactionResult failure(String accountId, BigDecimal amount, String reason) {
        TransactionResult r = new TransactionResult();
        r.accountId = accountId;
        r.amount = amount;
        r.success = false;
        r.reason = reason;
        return r;
    }


}

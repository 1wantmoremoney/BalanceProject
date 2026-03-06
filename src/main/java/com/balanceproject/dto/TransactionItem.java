package com.balanceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 单比交易请求
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:01
 */
@Data
public class TransactionItem {
    @NotBlank(message = "账户id不能为空")
    private String accountId;
    @NotNull(message = "转账金额不能为空")
    private BigDecimal amount;
}

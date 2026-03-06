package com.balanceproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 余额响应查询
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:12
 */
@Data
@AllArgsConstructor
public class BalanceResponse {
    private String accountId;
    private BigDecimal balance;

}

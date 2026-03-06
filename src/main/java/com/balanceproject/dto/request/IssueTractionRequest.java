package com.balanceproject.dto.request;

import com.balanceproject.dto.TransactionItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * Description: 批量交易请求
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:03
 */
@Data
public class IssueTractionRequest {
    /**
     * 为true时静止产生负的余额
     */
    private Boolean checkBalance = false;

    @Valid
    @NotEmpty(message = "transactions 不能为空")
    private List<TransactionItem>  items;


}

package com.balanceproject.dto.response;

import com.balanceproject.dto.TransactionResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Description: 批量交易结果响应
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:10
 */
@Data
@AllArgsConstructor
public class IssueTransactionsResponse {

    private List<TransactionResult> results;

}

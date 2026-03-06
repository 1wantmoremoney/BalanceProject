package com.balanceproject.controller;

import com.balanceproject.dto.TransactionResult;
import com.balanceproject.dto.request.IssueTractionRequest;
import com.balanceproject.dto.response.BalanceResponse;
import com.balanceproject.dto.response.IssueTransactionsResponse;
import com.balanceproject.service.BalanceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author: Jiangchangpeng
 * Date: 2026/03/06/09:40
 */
@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;


    /**
     * 交易
     * @param request
     * @return
     */
    @PostMapping("transaction")
    public IssueTransactionsResponse transaction(@RequestBody@Valid IssueTractionRequest request) {
        return balanceService.issueTransaction(request);
    }

    /**
     * 查询账户余额
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable@NotBlank String accountId) {
        return ResponseEntity.ok(balanceService.getBalance(accountId));
    }


}

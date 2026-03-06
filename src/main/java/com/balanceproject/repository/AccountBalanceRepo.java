package com.balanceproject.repository;

import com.balanceproject.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Description:
 * Author: Jiangchangpeng
 * Date: 2026/03/06/08:58
 */

public interface AccountBalanceRepo  extends JpaRepository<AccountBalance, Long> {


    AccountBalance getBalanceByAccountId(String account);
}

package com.balanceproject.repository;

import com.balanceproject.entity.BalanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 * Author: Jiangchangpeng
 * Date: 2026/03/06/08:59
 */
public interface BalanceTractionRepo extends JpaRepository<BalanceTransaction, Long> {
    
}

package com.balanceproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description: 交易流水
 * Author: Jiangchangpeng
 * Date: 2026/03/06/08:46
 */
@Data
@Entity
@Table(name = "balance_transaction")
public class BalanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id",nullable = false,length = 64)
    private String accountId;

    @Column(name = "amount" ,nullable = false,scale = 4,precision = 19)
    private BigDecimal amount;

    @Column(name = "ending_balance",nullable = false,scale = 4,precision = 19)
    private BigDecimal endingBalance;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}

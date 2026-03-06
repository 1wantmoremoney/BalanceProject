package com.balanceproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description: 账户余额
 * Author: Jiangchangpeng
 * Date: 2026/03/06/08:43
 */

@Entity
@Data
@Table(name = "account_balance")
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false, unique = true, length = 64)
    private String accountId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }



}

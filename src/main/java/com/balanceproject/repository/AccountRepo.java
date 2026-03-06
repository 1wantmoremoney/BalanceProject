package com.balanceproject.repository;

import com.balanceproject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author: Jiangchangpeng
 * Date: 2026/03/06/08:56
 */
public interface AccountRepo extends JpaRepository<Account, Long> {

    /** 批量查询存在的 accountId，用于校验 */
    List<Account> findByAccountIdIn(List<String> accountIds);


}

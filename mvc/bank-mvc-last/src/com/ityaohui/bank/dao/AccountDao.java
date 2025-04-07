package com.ityaohui.bank.dao;

import com.ityaohui.bank.pojo.Account;

import java.util.List;

/**
 * Author: 小牛
 * Date: 2025/4/7 18:19
 * Description:
 */
public interface AccountDao {
    int insert(Account act);

    int deleteById(Long id);

    int update(Account act);

    Account selectByActno(String actno);

    List<Account> selectAll();
}

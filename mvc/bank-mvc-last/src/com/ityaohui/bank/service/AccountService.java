package com.ityaohui.bank.service;

import com.ityaohui.bank.exceptions.AppException;
import com.ityaohui.bank.exceptions.MoneyNotEnoughException;

/**
 * Author: 小牛
 * Date: 2025/4/7 18:21
 * Description:
 */
public interface AccountService {
    void transfer(String fromActno, String toActno, Double money) throws MoneyNotEnoughException, AppException;
}

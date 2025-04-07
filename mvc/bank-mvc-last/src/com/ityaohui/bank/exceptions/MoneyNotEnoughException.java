package com.ityaohui.bank.exceptions;

/**
 * Author: 小牛
 * Date: 2025/4/6 17:37
 * Description: 余额不足异常类
 */

public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}
    public MoneyNotEnoughException(String msg){
        super(msg);
    }
}

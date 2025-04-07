package com.ityaohui.bank.service;

import com.ityaohui.bank.dao.AccountDao;
import com.ityaohui.bank.exceptions.AppException;
import com.ityaohui.bank.exceptions.MoneyNotEnoughException;
import com.ityaohui.bank.pojo.Account;
import com.ityaohui.bank.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: 小牛
 * Date: 2025/4/6 23:56
 * Description: service 翻译为业务
 * AccountService  专门处理Account业务的一个类
 * 在该类中应该编写纯业务代码。（只专注业务。不写别的，不和其他代码混合在一块）
 * 只希望专注业务，能够将业务完美实现，少量bug
 * <p>
 * 业务类一般起名：XxxService、XxxBiz....
 */
public class AccountService {

    AccountDao accountDao = new AccountDao();

    // 这里的方法起名，一定要体现出，你要处理的是什么业务。

    /**
     * 完成转账的业务逻辑
     *
     * @param fromActno
     * @param toActno
     * @param money
     */
    public void transfer(String fromActno, String toActno, Double money) throws MoneyNotEnoughException, AppException {
        // service 层事务控制
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            System.out.println("transfer " + conn);
            conn.setAutoCommit(false);
            // 查询余额是否充足
            Account fromAct = accountDao.selectByActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("对不起，余额不足！");
            }
            // 程序到这里，说明余额充足
            Account toAct = accountDao.selectByActno(toActno);
            // 修改余额
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);

            int count = accountDao.update(fromAct);
//            String s = null;
//            s.toString();
            count += accountDao.update(toAct);

            if (count != 2) {
                throw new AppException("账户转账异常！！！");
            }
            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            // 回滚事务,这里也可以不回滚，因为程序还没走到提交事务就已经抛出异常了。
//            conn.rollback();
            throw new AppException("账户转账异常！！！");
        } finally {
            if (conn != null) {
                DBUtils.close(conn, null, null);
            }
        }


    }
}

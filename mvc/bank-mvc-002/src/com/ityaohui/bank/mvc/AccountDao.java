package com.ityaohui.bank.mvc;

import com.ityaohui.bank.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: 小牛
 * Date: 2025/4/6 23:27
 * Description: 负责Account数据的增删改查
 * <p>
 * 1. 什么时DAO?
 * Data Access Object (数据访问对象)
 * 2. DAO实际上时一种设计模式，属于JavaEE的设计模式之一。（不是23种设计模式。）
 * 3. DAO只负责数据库表的crud，没有任何业务逻辑在里面。
 * 4. 没有任何业务逻辑，只负责表中数据增删改查的对象，有一个特殊的称谓：DAO对象
 * 5. 为什么叫AccountDao呢？
 * 这是因为这个dao时专门处理t_act这张表的
 */
public class AccountDao {

    public int insert(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into t_act(actno,balance) value(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2, act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, null);
        }
        return count;
    }

    public int deleteById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_act where id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, null);
        }
        return count;
    }

    public int update(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            System.out.println("update " + conn);
            String sql = "update t_act set balance = ?,actno = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, act.getBalance());
            ps.setString(2, act.getActno());
            ps.setLong(3, act.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, null);
        }
        return count;
    }

    public Account selectByActno(String actno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account act = null;
        try {
            conn = DBUtils.getConnection();
            System.out.println("selectByActno " + conn);
            String sql = "select id,actno,balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                // 封装
                act = new Account();
                act.setActno(actno);
                act.setId(id);
                act.setBalance(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, rs);
        }
        return act;
    }

    public List<Account> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> actList = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select id,actno,balance from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                String actno = rs.getString("actno");
                Account act = new Account(id, actno, balance);
                // 封装
                actList.add(act);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, ps, rs);
        }
        return actList;
    }
}

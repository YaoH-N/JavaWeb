package com.ityaohui.bank.web;

import com.ityaohui.bank.exceptions.MoneyNotEnoughException;
import com.ityaohui.bank.service.AccountService;
import com.ityaohui.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author: 小牛
 * Date: 2025/4/6 22:44
 * Description: 账户小程序
 */

/**
 * 账户小程序
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = Double.parseDouble(request.getParameter("money"));
        // 调用业务方法处理业务 (Model)
        AccountService accountService = new AccountServiceImpl();
        try {
            accountService.transfer(fromActno, toActno, money);
            // 执行到这里，说明成功了
            // 展示处理结果
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (MoneyNotEnoughException e) {
            // 转账失败了,余额不足
            response.sendRedirect(request.getContextPath() + "/moneynotenough.jsp");
        } catch (Exception e) {
            // 转账失败了
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }

    }
}

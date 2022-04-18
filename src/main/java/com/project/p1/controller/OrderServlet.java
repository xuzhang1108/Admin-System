package com.project.p1.controller;

import com.google.gson.Gson;
import com.project.p1.bean.Result;
import com.project.p1.bean.bo.OrderByPageBO;
import com.project.p1.bean.bo.OrderEditBO;
import com.project.p1.bean.vo.OrderEditVO;
import com.project.p1.bean.vo.OrdersByPageVO;
import com.project.p1.service.OrderService;
import com.project.p1.service.OrderServiceImpl;
import com.project.p1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {
    private Gson gson = new Gson();

    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.replace(req.getContextPath() + "/api/admin/order/", "");
        if ("order".equals(action)) {
            order(req, resp);
        } else if ("deleteOrder".equals(action)) {
            deleteOrder(req, resp);
        }
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        orderService.deleteOrder(Integer.parseInt(id));
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        OrderEditVO orderEditVO = orderService.order(Integer.parseInt(id));
        resp.getWriter().println(gson.toJson(Result.ok(orderEditVO)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.replace(req.getContextPath() + "/api/admin/order/", "");
        if ("ordersByPage".equals(action)) {
            ordersByPage(req, resp);
        } else if ("changeOrder".equals(action)) {
            changeOrder(req, resp);
        }
    }

    private void changeOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = HttpUtils.getRequestBody(req);
        OrderEditBO orderEditBO = gson.fromJson(requestBody, OrderEditBO.class);
        orderService.changeOrder(orderEditBO);
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 根据用户输入的条件来构建where条件语句，对订单结果进行筛选过滤查询
     * @param req
     * @param resp
     */
    private void ordersByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = HttpUtils.getRequestBody(req);
        OrderByPageBO orderByPageBO = gson.fromJson(requestBody, OrderByPageBO.class);
        OrdersByPageVO ordersByPageVO = orderService.ordersByPage(orderByPageBO);
        resp.getWriter().println(gson.toJson(Result.ok(ordersByPageVO)));
    }
}

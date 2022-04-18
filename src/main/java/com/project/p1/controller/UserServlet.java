package com.project.p1.controller;

import com.google.gson.Gson;
import com.project.p1.bean.Result;
import com.project.p1.bean.vo.UserInfoVO;
import com.project.p1.service.UserService;
import com.project.p1.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.replace(req.getContextPath() + "/api/admin/user/", "");
        if ("allUser".equals(action)) {
            allUsers(req, resp);
        } else if ("searchUser".equals(action)) {
            searchUser(req, resp);
        } else if ("deleteUser".equals(action)) {
            deleteUser(req, resp);
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        userService.deleteUser(Integer.parseInt(id));
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    private void searchUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String word = req.getParameter("word");
        List<UserInfoVO> userInfoVOList = userService.searchUser(word);
        resp.getWriter().println(gson.toJson(Result.ok(userInfoVOList)));
    }

    private void allUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<UserInfoVO> userInfoVOList = userService.allUsers();
        Result result = new Result();
        result.setCode(0);
        result.setData(userInfoVOList);
        resp.getWriter().println(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

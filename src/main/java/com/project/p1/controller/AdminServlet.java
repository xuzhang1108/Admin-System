package com.project.p1.controller;

import com.google.gson.Gson;
import com.project.p1.bean.Result;
import com.project.p1.bean.bo.AdminAddBO;
import com.project.p1.bean.bo.AdminLoginBO;
import com.project.p1.bean.bo.AdminSearchBO;
import com.project.p1.bean.bo.AdminUpdateBO;
import com.project.p1.bean.vo.AdminAddVO;
import com.project.p1.bean.vo.AdminInfoVO;
import com.project.p1.bean.vo.AdminLoginVO;
import com.project.p1.bean.vo.AdminUpdateVO;
import com.project.p1.service.AdminService;
import com.project.p1.service.AdminServiceImpl;
import com.project.p1.utils.Constant;
import com.project.p1.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private Gson gson = new Gson();

    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace(request.getContextPath() + "/api/admin/admin/", "");
        if ("allAdmins".equals(action)) {
            allAdmins(request, response);
        } else if ("getAdminsInfo".equals(action)) {
            getAdminsInfo(request, response);
        } else if ("deleteAdmins".equals(action)) {
            deleteAdmins(request, response);
        }
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        adminService.deleteAdmin(Integer.parseInt(id));
        response.getWriter().println(gson.toJson(Result.ok()));
    }


    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        AdminInfoVO adminInfoVO = adminService.getAdminsInfo(Integer.parseInt(id));
        response.getWriter().println(gson.toJson(Result.ok(adminInfoVO)));
    }

    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<AdminInfoVO> adminInfoVOList = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminInfoVOList);
        response.getWriter().println(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace(request.getContextPath() + "/api/admin/admin/", "");
        if ("login".equals(action)) {
            login(request, response);
        } else if ("getSearchAdmins".equals(action)) {
            getSearchAdmins(request, response);
        } else if ("addAdminss".equals(action)) {
            addAdmin(request, response);
        } else if ("updateAdminss".equals(action)) {
            updateAdmin(request, response);
        }
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminUpdateBO adminUpdateBO = gson.fromJson(requestBody, AdminUpdateBO.class);
        AdminUpdateVO adminUpdateVO = adminService.updateAdmin(adminUpdateBO);
        response.getWriter().println(gson.toJson(Result.ok(adminUpdateVO)));
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminAddBO adminAddBO = gson.fromJson(requestBody, AdminAddBO.class);
        AdminAddVO adminAddVO = adminService.addAdmin(adminAddBO);
        response.getWriter().println(gson.toJson(Result.ok(adminAddVO)));
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminSearchBO adminSearchBO = gson.fromJson(requestBody, AdminSearchBO.class);
        List<AdminInfoVO> adminInfoVOS =  adminService.getSearchAdmins(adminSearchBO);
        Result result = Result.ok(adminInfoVOS);
        response.getWriter().println(gson.toJson(result));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }
        String requestBody = outputStream.toString("utf-8");
//        System.out.println(requestBody);
        AdminLoginBO adminLoginBO = gson.fromJson(requestBody, AdminLoginBO.class);

        Result result = new Result();
        // 判空
        if (StringUtils.isEmpty(adminLoginBO.getEmail()) || StringUtils.isEmpty(adminLoginBO.getPwd())) {
            //TODO 现在引入了axios组件 不像之前直接将响应消息输出给浏览器 需要做输出逻辑处理
            // 需要将结果一JSON对象的形式返回给客户端 :{"code""10000,"message":"用户名和密码不能为空!"}
            result.setCode(10000);
            result.setMessage("用户名和密码不能为空!");
            response.getWriter().println(gson.toJson(result));
            return;
        }

        // 接下来就是登录操作了， 为了使项目符合MVC和三层架构要引入Service。
        /**
         * 登录产生三种情况：
         * 1.登录成功：code=200
         * 2.登录失败：code=404
         * 3.服务器内部出错：code=500
         */
        int code = adminService.login(adminLoginBO);
        if (code == Constant.SUCCESS) {
            // 登录成功
            // 写入session
            HttpSession session = request.getSession();
            System.out.println(request.getMethod() + " " + request.getRequestURI() + " " + session);
            session.setAttribute("email", adminLoginBO.getEmail());
            result.setCode(0);
            AdminLoginVO adminLoginVO = new AdminLoginVO(adminLoginBO.getEmail(), adminLoginBO.getEmail());
            result.setData(adminLoginVO);
        } else if (code == Constant.FAIL) {
            result.setCode(10000);
            result.setMessage("密码不正确");
        }

        response.getWriter().println(gson.toJson(result));
    }
}

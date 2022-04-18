package com.project.p1.filter;

import com.google.gson.Gson;
import com.project.p1.bean.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/*")
public class ApplicationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 设置允许跨域访问的响应头
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8085");
        resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization,Content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        // 除了登录以后的所有的后台管理系统的接口全部拦截 /api/admin/admin/login
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        //不让OPTIONS方法过来时每次都创建新的session对象，会造成系统资源问题
        if (!method.equals("OPTIONS")) {
            if (!requestURI.equals(req.getContextPath() + "/api/admin/admin/login")) {
                // 如果没有登录，则拦截；如果登录，则放行
                HttpSession session = req.getSession();
                System.out.println(req.getMethod() + " " + req.getRequestURI() + " " + session);
                Object email = session.getAttribute("email");
                if (email == null) {
                    resp.getWriter().println(new Gson().toJson(Result.error("当前接口仅允许登录后使用")));
                    return;
                }
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}

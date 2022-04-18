package com.project.p1.controller;

import com.google.gson.Gson;
import com.project.p1.bean.Result;
import com.project.p1.bean.Type;
import com.project.p1.bean.bo.AddGoodsBO;
import com.project.p1.bean.bo.AddTypeBO;
import com.project.p1.bean.vo.GoodsByTypeVO;
import com.project.p1.bean.vo.GoodsInfoVO;
import com.project.p1.service.GoodsService;
import com.project.p1.service.GoodsServiceImpl;
import com.project.p1.utils.FileUploadUtils;
import com.project.p1.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.VariableElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private Gson gson = new Gson();

    private GoodsService goodsService = new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.replace(req.getContextPath() + "/api/admin/goods/", "");
        if ("getType".equals(action)) {
            getType(req, resp);
        } else if ("getGoodsByType".equals(action)) {
            getGoodsByType(req, resp);
        } else if ("deleteType".equals(action)) {
            deleteType(req, resp);
        } else if ("getGoodsInfo".equals(action)) {
            getGoodsInfo(req, resp);
        } else if ("deleteGoods".equals(action)) {
            deleteGoods(req, resp);
        }
    }

    private void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        goodsService.deleteGoods(Integer.parseInt(id));
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getGoodsInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        GoodsInfoVO goodsInfoVO = goodsService.getGoodsInfo(Integer.parseInt(id));
        resp.getWriter().println(gson.toJson(Result.ok(goodsInfoVO)));
    }

    private void deleteType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeId = req.getParameter("typeId");
        goodsService.deleteType(Integer.parseInt(typeId));
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getGoodsByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeId = req.getParameter("typeId");
        // 判空
        if (StringUtils.isEmpty(typeId) || typeId.equals("")) {
            try {
                resp.getWriter().println(gson.toJson(Result.error("请求参数不能为空")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        List<GoodsByTypeVO> goodsByTypeVOList = goodsService.getGoodsByType(Integer.parseInt(typeId));
        resp.getWriter().println(gson.toJson(Result.ok(goodsByTypeVOList)));
    }

    /**
     * 加载商城的商品分类
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Type> typeList = goodsService.getType();
        resp.getWriter().println(gson.toJson(Result.ok(typeList)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.replace(req.getContextPath() + "/api/admin/goods/", "");
        if ("imgUpload".equals(action)) {
            imgUpload(req, resp);
        } else if ("addGoods".equals(action)) {
            addGoods(req, resp);
        } else if ("addType".equals(action)) {
            addType(req, resp);
        } else if ("updateGoods".equals(action)) {
            updateGoods(req, resp);
        }

    }

    private void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addGoods(req, resp);
    }

    private void addType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = HttpUtils.getRequestBody(req);
        AddTypeBO addTypeBO = gson.fromJson(requestBody, AddTypeBO.class);
        goodsService.addType(addTypeBO);
        resp.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = HttpUtils.getRequestBody(req);
        AddGoodsBO addGoodsBO = null;
        try {
            addGoodsBO = gson.fromJson(requestBody, AddGoodsBO.class);
        } catch (Exception e) {
            // 如果代码走到catch语句里面，说明前端页面传入的库存量，价格参数不合法
            // 如：库存传入“aaa”等字符串。
            resp.getWriter().println(gson.toJson(Result.error("参数不合法")));
            return;
        }
        goodsService.addGoods(addGoodsBO);
        resp.getWriter().println(gson.toJson(Result.ok()));

    }

    private void imgUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> parseRequest = FileUploadUtils.parseRequest(req);
        String file = (String) parseRequest.get("file");
        resp.getWriter().println(gson.toJson(Result.ok(file)));
    }
}

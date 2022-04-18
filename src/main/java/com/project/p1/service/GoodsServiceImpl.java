package com.project.p1.service;

import com.project.p1.bean.Goods;
import com.project.p1.bean.Specification;
import com.project.p1.bean.Type;
import com.project.p1.bean.bo.AddGoodsBO;
import com.project.p1.bean.bo.AddGoodsSpecBO;
import com.project.p1.bean.bo.AddTypeBO;
import com.project.p1.bean.vo.GoodsByTypeVO;
import com.project.p1.bean.vo.GoodsInfoVO;
import com.project.p1.bean.vo.GoodsVO;
import com.project.p1.dao.GoodsDao;
import com.project.p1.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    @Override
    public List<Type> getType() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        List<Type> typeList = goodsDao.getType();
        sqlSession.commit();
        sqlSession.close();
        return typeList;
    }

    @Override
    public List<GoodsByTypeVO> getGoodsByType(int typeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        List<GoodsByTypeVO> goodsList = goodsDao.getGoodsByType(typeId);
        sqlSession.commit();
        sqlSession.close();
        // 这里如果查询没有数据，返回null，必须形成一个空对象
        // 否则传到前端没法解析
        if (goodsList == null) {
            goodsList = new ArrayList<>();
        }
        return goodsList;
    }

    /**
     * 1.将信息保存到商品表
     * 2.规格的信息保存到规格表
     * 注意事项：两者需要满足同时成功，同时失败。即事物
     * 事物的隔离级别：
     *   读未提交 读已提交 可重复读 串行化 虚幻读
     *   虚幻读：一个事物可以读取到另外一个事物插入到数据库内的数据
     *   也就意味着在一个事物内，再次去读取，那么读取到的就是刚刚插入的主键
     *   mybatis已经帮我们封装好了
     *   useGenerateKeys=true keyProperty=id
     *   当执行完插入语句之后，原先的goods参数的id就会被赋值成功
     * @param addGoodsBO
     */
    @Override
    public void addGoods(AddGoodsBO addGoodsBO) {
        List<AddGoodsSpecBO> specList = addGoodsBO.getSpecList();
        Double price = specList.get(0).getUnitPrice();
        Integer stockNum = specList.get(0).getStockNum();
        for (int i = 1; i < specList.size(); i++) {
            if (price > specList.get(i).getUnitPrice()) {
                price = specList.get(i).getUnitPrice();
            }
            stockNum += specList.get(i).getStockNum();
        }
        //在执行插入语句之前，goods的id是null
        Goods goods = new Goods(null, addGoodsBO.getName(), addGoodsBO.getTypeId(), addGoodsBO.getImg(), stockNum, price, addGoodsBO.getDesc());
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        goodsDao.addGoods(goods);
        //执行完addGoods方法后，那么此时再去查询goods的id，发现就已经有值。使用goods.id即可获取到
        List<Specification> spec = new ArrayList<>();
        for (AddGoodsSpecBO addGoodsSpecBO : specList) {
            Specification specification = new Specification(null, addGoodsSpecBO.getSpecName(), addGoodsSpecBO.getStockNum(), addGoodsSpecBO.getUnitPrice(), goods.getId());
            spec.add(specification);
        }
        goodsDao.addSpecs(spec);
        //如果在开发阶段执行插入时出错了，下次再插入时会发现锁表的情况
        //此时可以把服务器关闭，重来就可以了
        //或者放入try catch中，报错那么执行回滚rollback操作
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addType(AddTypeBO addTypeBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        Type type = new Type(null, addTypeBO.getName());
        goodsDao.addType(type);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteType(int typeId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        goodsDao.deleteType(typeId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public GoodsInfoVO getGoodsInfo(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        Goods goods = goodsDao.getGoodsInfo(id);
        List<Specification> specList = goodsDao.getSpecList(id);
        List<AddGoodsSpecBO> addGoodsSpecBOList = new ArrayList<>();
        for (Specification specification : specList) {
            addGoodsSpecBOList.add(new AddGoodsSpecBO(specification.getPrice(), specification.getName(), specification.getStockNum()));
        }
        GoodsVO goodsVO = new GoodsVO(goods.getImage(), goods.getName(), goods.getTypeId(), goods.getDescription());
        GoodsInfoVO goodsInfoVO = new GoodsInfoVO(goodsVO, addGoodsSpecBOList);
        return goodsInfoVO;
    }

    @Override
    public void deleteGoods(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        goodsDao.deleteGoods(id);
        goodsDao.deleteSpec(id);
        sqlSession.commit();
        sqlSession.close();
    }
}

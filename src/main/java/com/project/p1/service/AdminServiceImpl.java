package com.project.p1.service;

import com.project.p1.bean.bo.AdminAddBO;
import com.project.p1.bean.bo.AdminLoginBO;
import com.project.p1.bean.bo.AdminSearchBO;
import com.project.p1.bean.bo.AdminUpdateBO;
import com.project.p1.bean.pojo.Admin;
import com.project.p1.bean.vo.AdminAddVO;
import com.project.p1.bean.vo.AdminInfoVO;
import com.project.p1.bean.vo.AdminUpdateVO;
import com.project.p1.dao.AdminDao;
import com.project.p1.utils.Constant;
import com.project.p1.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    // login的逻辑在数据库层面其实就是查询一条sql语句即可
    @Override
    public int login(AdminLoginBO adminLoginBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        Admin admin = new Admin(null, adminLoginBO.getEmail(), null, adminLoginBO.getPwd());
        int count = adminDao.login(admin);
        sqlSession.commit();
        sqlSession.close();
        if (count == 1) {
            return Constant.SUCCESS;
        }
        return Constant.FAIL;
    }

    @Override
    public List<AdminInfoVO> allAdmins() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        List<Admin> adminList = adminDao.allAdmins(new Admin());
        sqlSession.commit();
        sqlSession.close();
        List<AdminInfoVO> adminInfoVOList = new ArrayList<>();
        for (Admin admin : adminList) {
            AdminInfoVO adminInfoVO = new AdminInfoVO(admin.getId(), admin.getUsername(), admin.getNickname(), admin.getPassword());
            adminInfoVOList.add(adminInfoVO);
        }
        return adminInfoVOList;
    }


    @Override
    public List<AdminInfoVO> getSearchAdmins(AdminSearchBO adminSearchBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        Admin admin1 = new Admin(null, adminSearchBO.getEmail(), adminSearchBO.getNickname(), null);
        List<Admin> adminList = adminDao.allAdmins(admin1);
        sqlSession.commit();
        sqlSession.close();
        List<AdminInfoVO> adminInfoVOList = new ArrayList<>();
        for (Admin admin : adminList) {
            AdminInfoVO adminInfoVO = new AdminInfoVO(admin.getId(), admin.getUsername(), admin.getNickname(), admin.getPassword());
            adminInfoVOList.add(adminInfoVO);
        }
        return adminInfoVOList;
    }

    @Override
    public AdminInfoVO getAdminsInfo(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        Admin admin = adminDao.getAdmin(id);
        AdminInfoVO adminInfoVO = new AdminInfoVO(admin.getId(), admin.getUsername(), admin.getNickname(), admin.getPassword());
        return adminInfoVO;
    }


    @Override
    public AdminAddVO addAdmin(AdminAddBO adminAddBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        Admin admin = new Admin(null, adminAddBO.getEmail(), adminAddBO.getNickname(), adminAddBO.getPwd());
        adminDao.addAdmin(admin);
        sqlSession.commit();
        sqlSession.close();
        return new AdminAddVO(admin.getUsername(), admin.getNickname(), admin.getNickname());
    }

    @Override
    public void deleteAdmin(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        adminDao.deleteAdmin(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public AdminUpdateVO updateAdmin(AdminUpdateBO adminUpdateBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
        Admin admin = new Admin(adminUpdateBO.getId(), adminUpdateBO.getEmail(), adminUpdateBO.getNickname(), adminUpdateBO.getPwd());
        adminDao.updateAdmin(admin);
        sqlSession.commit();
        sqlSession.close();
        return new AdminUpdateVO(adminUpdateBO.getId(), adminUpdateBO.getEmail(), adminUpdateBO.getNickname(), adminUpdateBO.getPwd());
    }

}

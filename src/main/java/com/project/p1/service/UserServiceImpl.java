package com.project.p1.service;

import com.project.p1.bean.pojo.User;
import com.project.p1.bean.vo.UserInfoVO;
import com.project.p1.dao.UserDao;
import com.project.p1.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<UserInfoVO> allUsers() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.allUsers();
        sqlSession.commit();
        sqlSession.close();
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (User user : users) {
            UserInfoVO userInfoVO = new UserInfoVO(user.getId(), user.getEmail(), user.getNickname(), user.getRecipient(), user.getAddress(), user.getPhone());
            userInfoVOList.add(userInfoVO);
        }
        return userInfoVOList;
    }

    @Override
    public List<UserInfoVO> searchUser(String nickname) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.searchUser(nickname);
        sqlSession.commit();
        sqlSession.close();
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (User user : users) {
            UserInfoVO userInfoVO = new UserInfoVO(user.getId(), user.getEmail(), user.getNickname(), user.getRecipient(), user.getAddress(), user.getPhone());
            userInfoVOList.add(userInfoVO);
        }
        return userInfoVOList;
    }

    @Override
    public void deleteUser(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.deleteUser(id);
        sqlSession.commit();
        sqlSession.close();
    }

}

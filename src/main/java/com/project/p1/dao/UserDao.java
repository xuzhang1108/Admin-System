package com.project.p1.dao;

import com.project.p1.bean.pojo.Admin;
import com.project.p1.bean.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();

    List<User> searchUser(String nickname);

    void deleteUser(int id);
}

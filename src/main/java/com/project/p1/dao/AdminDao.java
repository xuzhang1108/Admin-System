package com.project.p1.dao;

import com.project.p1.bean.bo.AdminSearchBO;
import com.project.p1.bean.pojo.Admin;

import java.util.List;

public interface AdminDao {

    int login(Admin admin);

    List<Admin> allAdmins(Admin admin);

    Admin getAdmin(int id);

    void addAdmin(Admin admin);

    void deleteAdmin(int id);

    void updateAdmin(Admin admin);
}

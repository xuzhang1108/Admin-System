package com.project.p1.service;

import com.project.p1.bean.bo.AdminAddBO;
import com.project.p1.bean.bo.AdminLoginBO;
import com.project.p1.bean.bo.AdminSearchBO;
import com.project.p1.bean.bo.AdminUpdateBO;
import com.project.p1.bean.vo.AdminAddVO;
import com.project.p1.bean.vo.AdminInfoVO;
import com.project.p1.bean.vo.AdminUpdateVO;
import com.project.p1.bean.vo.UserInfoVO;

import java.util.List;

public interface UserService {

    List<UserInfoVO> allUsers();

    List<UserInfoVO> searchUser(String word);

    void deleteUser(int id);
}

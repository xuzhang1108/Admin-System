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

public interface AdminService {
    int login(AdminLoginBO adminLoginBO);

    List<AdminInfoVO> allAdmins();

    List<AdminInfoVO> getSearchAdmins(AdminSearchBO adminSearchBO);

    AdminInfoVO getAdminsInfo(int parseInt);

    AdminAddVO addAdmin(AdminAddBO adminAddBO);

    void deleteAdmin(int parseInt);

    AdminUpdateVO updateAdmin(AdminUpdateBO adminUpdateBO);
}

package com.xn.service;

import com.xn.entity.Admin;
import com.xn.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "adminService")
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin getAdmin(String username){
        return adminMapper.selectAdminLogin(username);
    }
    public int updateAdminByPrimaryKey(Admin admin){
        return adminMapper.updateByPrimaryKey(admin);
    }
    public int updateAdminByPrimaryKeySelective(Admin admin){
        return adminMapper.updateByPrimaryKeySelective(admin);
    }
}

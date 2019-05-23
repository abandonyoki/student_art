package com.xn.mapper;

import com.xn.entity.Admin;

public interface AdminMapper {
    int insert(Admin admin);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Admin admin);
    int updateByPrimaryKeySelective(Admin admin);
    Admin selectByPrimaryKey(Integer id);
    Admin selectAdminLogin(String username);

}

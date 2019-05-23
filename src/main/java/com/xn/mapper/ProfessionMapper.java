package com.xn.mapper;

import com.xn.entity.Profession;
import com.xn.entity.Student;
import com.xn.pojo.Page;

import java.util.List;

public interface ProfessionMapper {
    int insert(Profession profession);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Profession profession);
    int updateByPrimaryKeySelective(Profession profession);
    Profession selectByPrimaryKey(Integer id);
    List findPage(Page page);
    List findProfession();
}

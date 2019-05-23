package com.xn.mapper;

import com.xn.entity.Teacher;
import com.xn.pojo.Page;
import java.util.List;

public interface TeacherMapper {
    int insert(Teacher teacher);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Teacher teacher);
    int updateByPrimaryKeySelective(Teacher teacher);
    Teacher selectByPrimaryKey(Integer id);
    List findPage(Page page);
    Teacher selectTeacherLogin(String username);
}

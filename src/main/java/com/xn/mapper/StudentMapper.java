package com.xn.mapper;
import com.xn.entity.Student;
import com.xn.pojo.Page;

import java.util.List;

public interface StudentMapper {
    int insert(Student student);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Student student);
    int updateByPrimaryKeySelective(Student student);
    Student selectByPrimaryKey(Integer id);
    List findPage(Page page);
    Student selectStudentLogin(String username);
}

package com.xn.mapper;

import com.xn.entity.Course;
import com.xn.pojo.Page;

import java.util.List;

public interface CourseMapper {
    int insert(Course course);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Course course);
    int updateByPrimaryKeySelective(Course course);
    List findCourseList();
    List findCourseByProfessionId(Integer professionId);
    List findPage(Page page);
}

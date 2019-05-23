package com.xn.service;

import com.xn.entity.Class;
import com.xn.entity.Course;
import com.xn.mapper.ClassMapper;
import com.xn.mapper.CourseMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="courseService")
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public List findCourseByProfessionId(int professionId){
        return courseMapper.findCourseByProfessionId(professionId);
    }
    public List findCourseList(){
        return courseMapper.findCourseList();
    }
    public int updateCourseByPrimaryKey(Course course){
        return  courseMapper.updateByPrimaryKey(course);
    }
    public int updateCourseByPrimaryKeySelective(Course course){
        return courseMapper.updateByPrimaryKeySelective(course);
    }
    public void findPage(Page page){
        courseMapper.findPage(page);
    }
    public int insertCourse(Course course){
        return courseMapper.insert(course);
    }
    public int deleteCourse(int id){
        return courseMapper.deleteByPrimaryKey(id);
    }
}

package com.xn.service;

import com.xn.entity.Teacher;
import com.xn.mapper.TeacherMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "teacherService")
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    public Teacher getTeacher(String username){
        return teacherMapper.selectTeacherLogin(username);
    }
    public int updateTeacherByPrimaryKey(Teacher teacher){
        return teacherMapper.updateByPrimaryKey(teacher);
    }
    public int updateTeacherByPrimaryKeySelective(Teacher teacher){
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }
    public void findPage(Page page){
        teacherMapper.findPage(page);
    }
    public int insertTeacher(Teacher teacher){
        return teacherMapper.insert(teacher);
    }
    public int deleteTeacher(int id){
        return teacherMapper.deleteByPrimaryKey(id);
    }
}

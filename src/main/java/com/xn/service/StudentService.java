package com.xn.service;

import com.xn.entity.Student;
import com.xn.mapper.StudentMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "studentService")
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public Student getStudentByPrimaryKey(int id){
        return studentMapper.selectByPrimaryKey(id);
    }
    public Student getStudent(String username){
        return studentMapper.selectStudentLogin(username);
    }
    public int updateStudentByPrimaryKey(Student student){
        return  studentMapper.updateByPrimaryKey(student);
    }
    public int updateStudentByPrimaryKeySelective(Student student){
        return studentMapper.updateByPrimaryKeySelective(student);
    }
    public void findPage(Page page){
        studentMapper.findPage(page);
    }
    public int insertStudent(Student student){
        return studentMapper.insert(student);
    }
    public int deleteStudent(int id){
        return studentMapper.deleteByPrimaryKey(id);
    }
}

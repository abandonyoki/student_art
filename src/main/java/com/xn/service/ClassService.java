package com.xn.service;

import com.xn.entity.Class;
import com.xn.mapper.ClassMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="classService")
public class ClassService {
    @Autowired
    private ClassMapper classMapper;

    public int updateClassByPrimaryKey(Class c){
        return  classMapper.updateByPrimaryKey(c);
    }
    public int updateClassByPrimaryKeySelective(Class c){
        return classMapper.updateByPrimaryKeySelective(c);
    }
    public void findPage(Page page){
        classMapper.findPage(page);
    }
    public List findClass(){
        return classMapper.findClass();
    }
    public int insertClass(Class c){
        return classMapper.insert(c);
    }
    public int deleteClass(int id) throws DataIntegrityViolationException {
        return classMapper.deleteByPrimaryKey(id);
    }
}

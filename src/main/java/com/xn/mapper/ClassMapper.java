package com.xn.mapper;

import com.xn.entity.Class;
import com.xn.pojo.Page;
import java.util.List;

public interface ClassMapper {
    int insert(Class c);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Class c);
    int updateByPrimaryKeySelective(Class c);
    Class selectByPrimaryKey(Integer id);
    //模糊查询
    List findPage(Page page);
    List findClass();
}

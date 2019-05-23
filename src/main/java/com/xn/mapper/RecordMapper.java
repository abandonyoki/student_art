package com.xn.mapper;

import com.xn.entity.Record;
import com.xn.entity.Student;
import com.xn.pojo.Page;

import java.util.List;

public interface RecordMapper {
    int insert(Record record);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Record record);
    int updateByPrimaryKeySelective(Record record);
    Record selectByPrimaryKey(Integer id);
    List findPage(Page page);
}

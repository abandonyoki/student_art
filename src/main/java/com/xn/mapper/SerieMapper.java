package com.xn.mapper;

import com.xn.entity.Serie;
import com.xn.entity.Student;
import com.xn.pojo.Page;

import java.util.List;

public interface SerieMapper {
    int insert(Serie serie);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Serie serie);
    int updateByPrimaryKeySelective(Serie serie);
    Serie selectByPrimaryKey(Integer id);
    List findPage(Page page);
    List findSerie();
}

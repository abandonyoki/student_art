package com.xn.mapper;

import com.xn.entity.Design;
import com.xn.pojo.Page;
import java.util.List;

public interface DesignMapper {
    int insert(Design design);
    int insertSelective(Design design);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Design design);
    int updateByPrimaryKeySelective(Design design);
    Design selectByPrimaryKey(Integer id);
    List findPage(Page page);
    List findDesignList();
}

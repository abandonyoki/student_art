package com.xn.mapper;

import java.util.List;
import com.xn.entity.Notice;
import com.xn.pojo.Page;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    List findPage(Page page);
    List findNotice();
    
    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}
package com.xn.service;

import com.xn.entity.Notice;
import com.xn.mapper.NoticeMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "noticeService")
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void findPage(Page page) {
        noticeMapper.findPage(page);
    }

    public int insertSelective(Notice notice) {
        return  noticeMapper.insertSelective(notice);
    }

    public int updateByPrimaryKeySelective(Notice notice) {
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    public int deleteByPrimaryKey(int id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }
}

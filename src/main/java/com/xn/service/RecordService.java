package com.xn.service;

import com.xn.entity.Class;
import com.xn.entity.Record;
import com.xn.mapper.RecordMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="recordService")
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;
    public int updateRecordByPrimaryKey(Record record){
        return  recordMapper.updateByPrimaryKey(record);
    }
    public int updateRecordByPrimaryKeySelective(Record record){
        return recordMapper.updateByPrimaryKeySelective(record);
    }
    public void findPage(Page page){
        recordMapper.findPage(page);
    }
    public int insertRecord(Record record){
        return recordMapper.insert(record);
    }
    public int deleteRecord(int id){
        return recordMapper.deleteByPrimaryKey(id);
    }
}

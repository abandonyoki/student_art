package com.xn.service;

import com.xn.entity.Design;
import com.xn.mapper.DesignMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="designService")
public class DesignService {
    @Autowired
    private DesignMapper designMapper;
    public List findDesignList(){
        return designMapper.findDesignList();
    }
    public Design findDesign(int id){
        return  designMapper.selectByPrimaryKey(id);
    }
    public void findPage(Page page){
        designMapper.findPage(page);
    }
    public int updateDesignByPrimaryKey(Design design){
        return  designMapper.updateByPrimaryKey(design);
    }
    public int updateDesignByPrimaryKeySelective(Design design){
        return designMapper.updateByPrimaryKeySelective(design);
    }
    public int insertDesign(Design design){
        return designMapper.insert(design);
    }
    public int insertDesignSelective(Design design){
        return designMapper.insertSelective(design);
    }
    public int deleteDesign(int id){
        return designMapper.deleteByPrimaryKey(id);
    }
}

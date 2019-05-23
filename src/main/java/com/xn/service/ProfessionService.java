package com.xn.service;

import com.xn.entity.Profession;
import com.xn.mapper.ProfessionMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="professionService")
public class ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;
    public void findPage(Page page){
        professionMapper.findPage(page);
    }
    public List findProfession(){
        return professionMapper.findProfession();
    }
    public int updateProfessionByPrimaryKey(Profession profession){
        return  professionMapper.updateByPrimaryKey(profession);
    }
    public int updateProfessionByPrimaryKeySelective(Profession profession){
        return professionMapper.updateByPrimaryKeySelective(profession);
    }
    public int insertProfession(Profession profession){
        return professionMapper.insert(profession);
    }
    public int deleteProfession(int id){
        return professionMapper.deleteByPrimaryKey(id);
    }
}

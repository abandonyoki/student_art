package com.xn.service;


import com.xn.entity.Serie;
import com.xn.mapper.SerieMapper;
import com.xn.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="serieService")
public class SerieService {
    @Autowired
    private SerieMapper serieMapper;
    public void findPage(Page page){
       serieMapper.findPage(page);
    }
    public List findSerie(){
        return serieMapper.findSerie();
    }
    public int updateSerieByPrimaryKey(Serie serie){
        return  serieMapper.updateByPrimaryKey(serie);
    }
    public int updateSerieByPrimaryKeySelective(Serie serie){
        return serieMapper.updateByPrimaryKeySelective(serie);
    }
    public int insertSerie(Serie serie){
        return serieMapper.insert(serie);
    }
    public int deleteSerie(int id){
        return serieMapper.deleteByPrimaryKey(id);
    }
}

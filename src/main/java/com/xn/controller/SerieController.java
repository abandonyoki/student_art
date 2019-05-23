package com.xn.controller;

import com.xn.entity.Login;
import com.xn.entity.Serie;
import com.xn.pojo.Page;
import com.xn.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/serie")
public class SerieController {
    @Autowired
    private SerieService serieService;

    @RequestMapping(value="/serie")
    public ModelAndView findSerieList(Login user, HttpSession session, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("serie");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page getSeriePage(Page page,Serie serie){
        page.setSearchParam(serie);
        serieService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/findSerie")
    public List findSerie(){
        List list = serieService.findSerie();
        return list;
    }

    @ResponseBody
    @RequestMapping(value="/insertSerie")
    public Map<String,Object> insertSerie(Serie serie){
        Map returnMap = new HashMap<String,Object>();
        int i = serieService.insertSerie(serie);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateSerie")
    public Map<String,Object> updateSerie(Serie serie){
        Map returnMap = new HashMap<String,Object>();
        int i = serieService.updateSerieByPrimaryKeySelective(serie);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSerie")
    public Map<String,Object> deleteSerie(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = serieService.deleteSerie(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

package com.xn.controller;

import com.xn.entity.Profession;
import com.xn.pojo.Page;
import com.xn.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="profession")
public class ProfessionController {
    @Autowired
    private ProfessionService professionService;

    @RequestMapping(value="profession")
    public ModelAndView getProfessionList(){
        ModelAndView mv = new ModelAndView("profession");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findStudentPage(Page page, Profession profession){
        page.setSearchParam(profession);
        professionService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="findProfession")
    public List findProfession(){
        List list = professionService.findProfession();
        return list;
    }
    @ResponseBody
    @RequestMapping(value="/insertProfession")
    public Map<String,Object> insertProfession(Profession profession){
        Map returnMap = new HashMap<String,Object>();
        int i = professionService.insertProfession(profession);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateProfession")
    public Map<String,Object> updateStudent(Profession profession){
        Map returnMap = new HashMap<String,Object>();
        int i = professionService.updateProfessionByPrimaryKeySelective(profession);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProfession")
    public Map<String,Object> deleteProfession(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = professionService.deleteProfession(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

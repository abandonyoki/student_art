package com.xn.controller;

import com.xn.entity.Class;
import com.xn.pojo.Page;
import com.xn.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @RequestMapping(value="/class")
    public ModelAndView getClassList(){
        ModelAndView mv = new ModelAndView("class");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findClassPage(Page page, Class c){
        page.setSearchParam(c);
        classService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/findClass")
    public List findList(){
        List list = classService.findClass();
        return list;
    }
    @ResponseBody
    @RequestMapping(value="/insertClass")
    public Map<String,Object> insertClass(Class c){
        Map returnMap = new HashMap<String,Object>();
        int i = classService.insertClass(c);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateClass")
    public Map<String,Object> updateClass(Class c){
        Map returnMap = new HashMap<String,Object>();
        int i = classService.updateClassByPrimaryKeySelective(c);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteClass")
    public Map<String,Object> deleteClass(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = classService.deleteClass(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

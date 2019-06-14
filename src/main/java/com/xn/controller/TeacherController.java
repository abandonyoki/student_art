package com.xn.controller;

import com.xn.entity.Login;
import com.xn.entity.Student;
import com.xn.entity.Teacher;
import com.xn.pojo.Page;
import com.xn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="teacher")
    public ModelAndView getTeacherList(){
        ModelAndView mv = new ModelAndView("teacher");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findTeacherPage(Page page, Teacher teacher){
        page.setSearchParam(teacher);
        teacherService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/insertTeacher")
    public Map<String,Object> insertStudent(Teacher teacher){
        Map returnMap = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String stuno = sdf.format(new Date());
        teacher.setTeaPwd("123456");
        int i = teacherService.insertTeacher(teacher);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateTeacher")
    public Map<String,Object> updateStudent(Teacher teacher){
        Map returnMap = new HashMap<String,Object>();
        int i = teacherService.updateTeacherByPrimaryKeySelective(teacher);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTeacher")
    public Map<String,Object> deleteStudent(int id){
        Map returnMap = new HashMap<String,Object>();

        int i = teacherService.deleteTeacher(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

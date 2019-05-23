package com.xn.controller;

import com.xn.entity.Login;
import com.xn.entity.Student;
import com.xn.pojo.Page;
import com.xn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/student")
    public ModelAndView findStudentList(Login user, HttpSession session, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("student");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findStudentPage(Page page, Student student){
        page.setSearchParam(student);
        studentService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/tea_student")
    public ModelAndView tea_student(Login user, HttpSession session, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("tea_student");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/insertStudent")
    public Map<String,Object> insertStudent(Student student){
        Map returnMap = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String stuno = sdf.format(new Date());
        student.setStuPwd("123456");
        int i = studentService.insertStudent(student);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateStudent")
    public Map<String,Object> updateStudent(Student student){
        Map returnMap = new HashMap<String,Object>();
        int i = studentService.updateStudentByPrimaryKeySelective(student);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteStudent")
    public Map<String,Object> deleteStudent(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = studentService.deleteStudent(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

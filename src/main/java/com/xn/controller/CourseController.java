package com.xn.controller;

import com.xn.entity.Course;
import com.xn.entity.Login;
import com.xn.pojo.Page;
import com.xn.service.CourseService;
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
@RequestMapping(value="course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value="/course")
    public ModelAndView getCourseList(){
        ModelAndView mv = new ModelAndView("course");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findStudentPage(Page page, Course course){
        page.setSearchParam(course);
        courseService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/findCourse")
    public List finCourse(HttpSession session, HttpServletRequest request){
        Login login = (Login)session.getAttribute("user");
        List list = courseService.findCourseByProfessionId(login.getProfessionId());
        return list;
    }

    @ResponseBody
    @RequestMapping(value="/findCourseList")
    public List finCourseList(HttpSession session, HttpServletRequest request){
        List list = courseService.findCourseList();
        return list;
    }

    @ResponseBody
    @RequestMapping(value="/insertCourse")
    public Map<String,Object> insertCourse(Course course){
        Map returnMap = new HashMap<String,Object>();
        int i = courseService.insertCourse(course);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateCourse")
    public Map<String,Object> updateCourse(Course course){
        Map returnMap = new HashMap<String,Object>();
        int i = courseService.updateCourseByPrimaryKeySelective(course);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCourse")
    public Map<String,Object> deleteCourse(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = courseService.deleteCourse(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

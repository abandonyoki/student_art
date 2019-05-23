package com.xn.controller;

import com.xn.entity.*;
import com.xn.service.AdminService;
import com.xn.service.DesignService;
import com.xn.service.StudentService;
import com.xn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private DesignService designService;

    @RequestMapping(value="/login")
    @ResponseBody
    public Map<String,Object> adminLogin(Login user, HttpSession session, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        //管理员校验3
        if("3".equals((user.getRole()))){
        Admin admin = adminService.getAdmin(user.getUsername());
        if(admin != null){
            if(admin.getPassword().equals(user.getPassword())){
                user.setId(admin.getId());
                session.setAttribute("user",user);
                //登陆成功
                map.put("isFlag",0);
            }else{
                //密码错误
                map.put("isFlag",1);
            }
        }else{
            //角色选择错误
            map.put("isFlag",2);
        }
    }
        if("2".equals((user.getRole()))){
            Teacher teacher = teacherService.getTeacher(user.getUsername());
            if(teacher != null){
                if(teacher.getTeaPwd().equals(user.getPassword())){
                    user.setId(teacher.getId());
                    user.setProfessionId(teacher.getProfessionId());
                    user.setUsername(teacher.getTeaName());
                    session.setAttribute("user",user);
                    //登陆成功
                    map.put("isFlag",0);
                }else{
                    //密码错误
                    map.put("isFlag",1);
                }
            }else{
                //角色选择错误
                map.put("isFlag",2);
            }
        }
        if("1".equals((user.getRole()))){
            Student student = studentService.getStudent(user.getUsername());
            if(student != null){
                if(student.getStuPwd().equals(user.getPassword())){
                    user.setId(student.getId());
                    user.setProfessionId(student.getProfessionId());
                    user.setUsername(student.getStuName());
                    session.setAttribute("user",user);
                    //登陆成功
                    map.put("isFlag",0);
                }else{
                    //密码错误
                    map.put("isFlag",1);
                }
            }else{
                //角色选择错误
                map.put("isFlag",2);
            }
        }
        return map;
}
    @RequestMapping(value="/main")
    public String getMainPage(Login user,HttpSession session,HttpServletRequest request){
        return "main";
    }
    @ResponseBody
    @RequestMapping(value="/editPassword")
    public Map<String,Object> editPassword(String password,HttpSession session){
        Map<String,Object> returnMap = new HashMap<>();
        Login login = (Login)session.getAttribute("user");
        int i = 0;
        if("1".equals(login.getRole())){//学生
            Student student = new Student();
            student.setId(login.getId());
            student.setStuPwd(password);
            i = studentService.updateStudentByPrimaryKeySelective(student);
        }
        if("2".equals(login.getRole())){//老师
            Teacher teacher = new Teacher();
            teacher.setId(login.getId());
            teacher.setTeaPwd(password);
            i = teacherService.updateTeacherByPrimaryKeySelective(teacher);
        }
        if("3".equals(login.getRole())){//管理员
            Admin admin = new Admin();
            admin.setId(login.getId());
            admin.setPassword(password);
            i = adminService.updateAdminByPrimaryKeySelective(admin);
        }
        if(i == 0){
            returnMap.put("showInfo","修改失败");
        }else{
            returnMap.put("showInfo","修改成功");
        }
        return returnMap;
    }
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value="/home")
    public ModelAndView home(HttpSession session){
        ModelAndView mv = new ModelAndView("home");
        List list = designService.findDesignList();
        Iterator<Design> iterator = list.iterator();
        while (iterator.hasNext()){
            Design design = iterator.next();
            if(design.getImgUrl().contains(".psd"))
                iterator.remove();
        }
        mv.addObject("list",list);
        return mv;
    }
}

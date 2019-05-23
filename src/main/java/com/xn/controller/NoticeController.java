package com.xn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xn.entity.Student;
import com.xn.service.StudentService;
import com.xn.util.SendMailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xn.pojo.Page;
import com.xn.entity.Login;
import com.xn.entity.Notice;
import com.xn.service.NoticeService;

@Controller
@RequestMapping ( "/notice" )
public class NoticeController {
  

    @Autowired
    private NoticeService noticeService;

	@Autowired
    private StudentController studentController;
	
    @RequestMapping(value="notice")
   	public ModelAndView getMainPage(Login tuser,HttpSession session,HttpServletRequest request){
    	ModelAndView mv = new ModelAndView("notice");
   		return mv;
   	}
   	
    
    @ResponseBody
    @RequestMapping(value= "/query")
    public Page findNoticePage( Page page , Notice notice) {
    	page.setSearchParam(notice);
    	noticeService.findPage(page);
        return page;
    }
    
    
    @ResponseBody
    @RequestMapping(value= "/insertNotice")
    public Map<String,Object> addNotice(Notice notice,HttpServletRequest request) {
        Integer classId = null;
        Integer professionId = null;
        Integer serieId = null;
        if(request.getParameter("classId") != null) {
            classId = Integer.parseInt(request.getParameter("classId"));
        }
        if(request.getParameter("professionId") != null){
            professionId = Integer.parseInt(request.getParameter("professionId"));
        }
        if(request.getParameter("serieId") != null){
            serieId = Integer.parseInt(request.getParameter("serieId"));
        }
        Student student = new Student(classId,professionId,serieId);
        Page page = new Page();
       /* page.setCurrentPage(1);
        page.setPageSize(10);
        page.setPages(1);
        page.setTotal(0);
        page.setRows(null);*/
        page.setSearchParam(student);
        page = studentController.findStudentPage(page,student);
        String[] recivers = new String[page.getTotal()];
        /*for (Object s:page.getRows()) {
            student = (Student)s;
            for(int i = 0;i<page.getTotal();i++){
                recivers[i] = student.getMailBox();
            }
        }*/
        Object[] arrayList = page.getRows().toArray();
        for(int i = 0;i<page.getTotal();i++){
            recivers[i]= ((Student)arrayList[i]).getMailBox();
        }
        SendMailInfo.sendTextMail(notice.getContent(),notice.getTitle(),recivers);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        notice.setCreateDate(sdf.format(new Date()));
    	int i =noticeService.insertSelective(notice);
    	Map<String,Object> returnMap = new HashMap<String,Object>();
        if (i == 0)
        	returnMap.put("showInfo", "添加失败！");
        else
        	returnMap.put("showInfo", "添加成功！");
        return returnMap;
    }
    
    
    @ResponseBody
    @RequestMapping(value= "/updateNotice")
    public Map<String,Object> updateNotice(Notice notice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        notice.setCreateDate(sdf.format(new Date()));
        int i =noticeService.updateByPrimaryKeySelective(notice);
    	Map<String,Object> returnMap = new HashMap<String,Object>();
        if (i == 0)
        	returnMap.put("showInfo", "修改失败！");
        else
        	returnMap.put("showInfo", "修改成功！");
        return returnMap;
    }
    
    @ResponseBody
    @RequestMapping(value= "/deleteNotice")
    public Map<String,Object> deleteNotice(int id) {
    	int i =noticeService.deleteByPrimaryKey(id);
    	Map<String,Object> returnMap = new HashMap<String,Object>();
        if (i == 0)
        	returnMap.put("showInfo", "删除失败！");
        else
        	returnMap.put("showInfo", "删除成功！");
        return returnMap;
    }
    
    

}

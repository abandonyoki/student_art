package com.xn.controller;

import com.xn.entity.Design;
import com.xn.entity.Login;
import com.xn.entity.Record;
import com.xn.entity.Student;
import com.xn.pojo.Page;
import com.xn.service.DesignService;
import com.xn.service.RecordService;
import com.xn.service.StudentService;
import com.xn.util.FileOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="design")
public class DesignController {
    @Autowired
    private DesignService designService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RecordService recordService;

    @RequestMapping(value="/stu_design")
    public ModelAndView getdesignList(){
        ModelAndView mv = new ModelAndView("stu_design");
        return mv;
    }

    @RequestMapping(value="/design")
    public ModelAndView getdesignListAdmin(){
        ModelAndView mv = new ModelAndView("design");
        return mv;
    }
    @ResponseBody
    @RequestMapping(value="/query")
    public Page findDesignPage(Page page, Design design){
        page.setSearchParam(design);
        designService.findPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value="/audit")
    public Map<String,Object> audit(Design design,HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<>();
        int i = designService.updateDesignByPrimaryKeySelective(design);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;

    }

    @RequestMapping(value = "/tea_design")
    public ModelAndView tea_design(Login user,HttpSession session,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("tea_design");
        return mv;
    }
    @RequestMapping(value="/down")
    public void down(Integer id, HttpSession session, HttpServletRequest request, HttpServletResponse response){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Design design = designService.findDesign(id);
            Record record = new Record();
            String realPath = request.getSession().
                          getServletContext().getRealPath("/")+design.getImgUrl();
            FileOperate.down(request, response, realPath);
            record.setOperateType("下载");
            record.setOperateTime(sdf.format(new Date()));
            record.setUsername(((Login)session.getAttribute("user")).getUsername());
            record.setDesignName(design.getDesignName());
            recordService.insertRecord(record);
    }

    @ResponseBody
    @RequestMapping(value="/insertDesign")
    public Map<String,Object> insertStudent(Design design, @RequestParam("files") CommonsMultipartFile[] files, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //获取学生信息
        Student student = studentService.getStudentByPrimaryKey(design.getStuId());
        Record record = new Record();
        Map returnMap = new HashMap<String,Object>();
        try {
            if(!files[0].isEmpty()){
                if(files[0].getSize() > 115485760 ){
                    returnMap.put("showInfo","文件过大");
                    return returnMap;
                }
                String filePath = request.getSession().getServletContext().getRealPath("/")+"upload/"+files[0].getOriginalFilename();
                String savePath = "upload/"+files[0].getOriginalFilename();
                design.setImgUrl(savePath);
                files[0].transferTo(new File(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        design.setCreateTime(sdf.format(new Date()));
        design.setStatus("0");
        design.setClassId(student.getClassId());
        design.setProfessionId(student.getProfessionId());
        int i = designService.insertDesignSelective(design);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            record.setOperateType("上传");
            record.setOperateTime(design.getCreateTime());
            record.setUsername(student.getStuName());
            record.setDesignName(design.getDesignName());
            recordService.insertRecord(record);
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateDesign")
    public Map<String,Object> updateStudent(Design design,@RequestParam("files") CommonsMultipartFile[] files,HttpServletRequest request){
        Map returnMap = new HashMap<String,Object>();
        try {
            if(!files[0].isEmpty()){
                String filePath = request.getSession().getServletContext().getRealPath("/")+"upload/"+files[0].getOriginalFilename();
                String savePath = "upload/"+files[0].getOriginalFilename();
                design.setImgUrl(savePath);
                files[0].transferTo(new File(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = designService.updateDesignByPrimaryKeySelective(design);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDesign")
    public Map<String,Object> deleteDesign(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = designService.deleteDesign(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

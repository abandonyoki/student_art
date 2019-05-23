package com.xn.controller;

import com.xn.entity.Record;
import com.xn.pojo.Page;
import com.xn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value="/record")
    public ModelAndView getTeacherList(){
        ModelAndView mv = new ModelAndView("record");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/query")
    public Page findRecordPage(Page page, Record record){
        page.setSearchParam(record);
        recordService.findPage(page);
        return page;
    }
    @ResponseBody
    @RequestMapping(value="/insertRecord")
    public Map<String,Object> insertRecord(Record record){
        Map returnMap = new HashMap<String,Object>();
        int i = recordService.insertRecord(record);
        if(i == 0){
            returnMap.put("showInfo","添加失败");
        }
        if(i == 1){
            returnMap.put("showInfo","添加成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value="/updateRecord")
    public Map<String,Object> updateRecord(Record record){
        Map returnMap = new HashMap<String,Object>();
        int i = recordService.updateRecordByPrimaryKeySelective(record);
        if(i == 0){
            returnMap.put("showInfo","更新失败");
        }
        if(i == 1){
            returnMap.put("showInfo","更新成功");
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRecord")
    public Map<String,Object> deleteRecord(int id){
        Map returnMap = new HashMap<String,Object>();
        int i = recordService.deleteRecord(id);
        if(i == 0){
            returnMap.put("showInfo","删除失败");
        }
        if(i == 1){
            returnMap.put("showInfo","删除成功");
        }
        return returnMap;
    }
}

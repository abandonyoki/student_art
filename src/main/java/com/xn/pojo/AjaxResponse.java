package com.xn.pojo;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxResponse {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 常量代码
     */
    public final static Integer CODE_SUCCESS = 1;
    /**
     * 常量代码
     */
    public final static Integer CODE_ERROR = 1;
    /**
     * 常量消息 成功
     */
    public final static String  MESSAGE_SUCCESS = "Operation Successed";
    /**
     * 常量消息 失败
     */
    public final static String  MESSAGE_ERROR = "Operation Error";

    public AjaxResponse() {

    }

    public AjaxResponse(Integer code) {
        this.code = code;
    }

    public AjaxResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 以返回参数作为初始化条件
     * @param isSuccess
     */
    public  AjaxResponse(boolean isSuccess){
        this();
        if(isSuccess){
            this.toSuccess();
        }else{
            this.toError();
        }
    }

    /**
     * 传递任意类型
     */
    public AjaxResponse(Object object, HttpServletResponse httpServletResponse){
        try {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=utf-8");
            //序列化
            SerializeWriter out = new SerializeWriter();
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.write(object);
            out.writeTo(httpServletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 操作成功实例
     */
    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.code = AjaxResponse.CODE_SUCCESS;
        ajaxResponse.message = AjaxResponse.MESSAGE_SUCCESS;
        return ajaxResponse;
    }
    /**
     * 操作成功实例
     * 携带数据
     */
    public static AjaxResponse success(Object data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.code = AjaxResponse.CODE_SUCCESS;
        ajaxResponse.message = AjaxResponse.MESSAGE_SUCCESS;
        ajaxResponse.data = data;
        return ajaxResponse;
    }
    /**
     * 操作失败实例
     * 无数据
     */
    public static AjaxResponse error(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.code = AjaxResponse.CODE_ERROR;
        ajaxResponse.message = AjaxResponse.MESSAGE_ERROR;
        return ajaxResponse;
    }
    /**
     * 操作失败实例
     * 携带数据
     */
    public static AjaxResponse error(Object data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.code = AjaxResponse.CODE_ERROR;
        ajaxResponse.message = AjaxResponse.MESSAGE_ERROR;
        ajaxResponse.data = data;
        return ajaxResponse;
    }

    public AjaxResponse toSuccess(){
        this.code = AjaxResponse.CODE_SUCCESS;
        this.message = AjaxResponse.MESSAGE_SUCCESS;
        return this;
    }
    public AjaxResponse toError(){
        this.code = AjaxResponse.CODE_ERROR;
        this.message = AjaxResponse.MESSAGE_ERROR;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.xn.entity;

import java.util.Date;

public class Design {
    private Integer id;
    /**
     * 作品名称
     */
    private String  designName;
    /**
     * 作品描述
     */
    private String designIntroduce;
    /**
     * 作品链接
     */
    private String imgUrl;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 作品评价
     */
    private String advice;
    private String createTime;
    private Integer stuId;
    private String stuName;
    private Integer courseId;
    private String courseName;
    private Integer classId;
    private String className;
    private Integer professionId;
    private String professionName;

    public String getDesignIntroduce() {
        return designIntroduce;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public String getDesignIntegerroduce() {
        return designIntroduce;
    }

    public void setDesignIntroduce(String designIntroduce) {
        this.designIntroduce = designIntroduce;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

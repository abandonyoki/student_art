package com.xn.entity;

public class Teacher {
    private Integer id;
    private String teaNo;
    private String teaName;
    private String teaSex;
    private Integer teaAge;
    private String teaPhone;
    private String teaPwd;
    private Integer professionId;
    private String professionName;
    private Integer serieId;
    private String serieName;

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeaNo() {
        return teaNo;
    }

    public void setTeaNo(String teaNo) {
        this.teaNo = teaNo.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName.trim();
    }

    public String getTeaPwd() {
        return teaPwd;
    }

    public void setTeaPwd(String teaPwd) {
        this.teaPwd = teaPwd.trim();
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone;
    }

    public Integer getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(Integer teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getSerieName() {
        return serieName;
    }

    public void setSerieName(String serieName) {
        this.serieName = serieName;
    }
}

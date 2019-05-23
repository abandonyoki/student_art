package com.xn.entity;

import java.util.Date;

public class Record {
    private Integer id;
    /**
     * 操作类型
     */

    private String operateType;
    /**
     * 操作时间
     */
    private String  operateTime;
    /**
     * 操作人
     */
    private String username;

    /**
     * 操作作品名称
     * @return
     */
    private String designName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }
}

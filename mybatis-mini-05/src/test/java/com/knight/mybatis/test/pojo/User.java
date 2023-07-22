package com.knight.mybatis.test.pojo;

import java.util.Date;

/**
 * @desc
 * @author knight
 * @date 2023/7/1
 */
public class User {
    private Long id;
    private String userId;

    private String userName;
    private String userHead;
    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(Long id, String uid) {
        this.id = id;
        this.userId = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

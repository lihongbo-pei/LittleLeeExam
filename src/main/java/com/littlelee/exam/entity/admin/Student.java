package com.littlelee.exam.entity.admin;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 考生实体类
 * @author littlelee
 * @date 2020/3/2 16:32
 */
@Component
public class Student {

    private Long id;
    private Long subjectId;//所属学科专业ID
    private String name;//登录姓名
    private String password;//登录密码
    private String trueName;//姓名
    private String tel;//手机号
    private Date createTime;//注册时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

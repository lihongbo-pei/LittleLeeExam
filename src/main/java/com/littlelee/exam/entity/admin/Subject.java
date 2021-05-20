package com.littlelee.exam.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 学科专业实体类
 * @author littlelee
 * @date 2020/3/1 22:14
 */
@Component
public class Subject {
    private Long id;
    private String name;//学科名称
    private String remark;//学科备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

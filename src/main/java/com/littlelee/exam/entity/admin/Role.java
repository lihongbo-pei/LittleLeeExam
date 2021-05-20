package com.littlelee.exam.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 角色 role 实体
 * @author littlelee
 * @date 2020/3/9 0:09
 */
@Component
public class Role {
	
	private Long id;
	
	private String name;
	
	private String remark;//角色备注

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

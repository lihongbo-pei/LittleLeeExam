package com.littlelee.exam.service.admin.impl;
/**
 * 角色role的实现类
 */
import java.util.List;
import java.util.Map;

import com.littlelee.exam.dao.admin.RoleDao;
import com.littlelee.exam.entity.admin.Role;
import com.littlelee.exam.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int add(Role role) {
		return roleDao.add(role);
	}

	@Override
	public int edit(Role role) {
		return roleDao.edit(role);
	}

	@Override
	public int delete(Long id) {
		return roleDao.delete(id);
	}

	@Override
	public List<Role> findList(Map<String, Object> queryMap) {
		return roleDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return roleDao.getTotal(queryMap);
	}

	@Override
	public Role find(Long id) {
		return roleDao.find(id);
	}

}

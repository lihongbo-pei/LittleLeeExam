package com.littlelee.exam.service.admin.impl;

/**
 * 菜单管理实现类
 */

import java.util.List;
import java.util.Map;

import com.littlelee.exam.dao.admin.MenuDao;
import com.littlelee.exam.entity.admin.Menu;
import com.littlelee.exam.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public int add(Menu menu) {
		return menuDao.add(menu);
	}

	@Override
	public List<Menu> findList(Map<String, Object> queryMap) {
		return menuDao.findList(queryMap);
	}

	@Override
	public List<Menu> findTopList() {
		return menuDao.findTopList();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return menuDao.getTotal(queryMap);
	}

	@Override
	public int edit(Menu menu) {
		return menuDao.edit(menu);
	}

	@Override
	public int delete(Long id) {
		return menuDao.delete(id);
	}

	@Override
	public List<Menu> findChildernList(Long parentId) {
		return menuDao.findChildernList(parentId);
	}

	@Override
	public List<Menu> findListByIds(String ids) {
		return menuDao.findListByIds(ids);
	}

}

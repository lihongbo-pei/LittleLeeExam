package com.littlelee.exam.service.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Menu;
import org.springframework.stereotype.Service;


/**
 * 菜单管理service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface MenuService {
	public int add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
	public List<Menu> findTopList();
	public int getTotal(Map<String, Object> queryMap);
	public int edit(Menu menu);
	public int delete(Long id);
	public List<Menu> findChildernList(Long parentId);
	public List<Menu> findListByIds(String ids);
}

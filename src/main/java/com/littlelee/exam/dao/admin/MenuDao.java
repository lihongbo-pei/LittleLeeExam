package com.littlelee.exam.dao.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 菜单管理dao
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface MenuDao {
	int add(Menu menu);
	List<Menu> findList(Map<String, Object> queryMap);
	List<Menu> findTopList();
	int getTotal(Map<String, Object> queryMap);
	int edit(Menu menu);
	int delete(Long id);
	List<Menu> findChildernList(Long parentId);
	List<Menu> findListByIds(String ids);
}

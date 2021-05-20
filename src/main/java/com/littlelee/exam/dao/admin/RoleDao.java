package com.littlelee.exam.dao.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色role dao
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface RoleDao {
	int add(Role role);
	int edit(Role role);
	int delete(Long id);
	List<Role> findList(Map<String, Object> queryMap);
	int getTotal(Map<String, Object> queryMap);
	Role find(Long id);
}

package com.littlelee.exam.service.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Role;
import org.springframework.stereotype.Service;

/**
 * 角色role service
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface RoleService {
	int add(Role role);
	int edit(Role role);
	int delete(Long id);
	List<Role> findList(Map<String, Object> queryMap);
	int getTotal(Map<String, Object> queryMap);
	Role find(Long id);
}

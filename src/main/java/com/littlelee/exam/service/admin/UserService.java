package com.littlelee.exam.service.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.User;
import org.springframework.stereotype.Service;


/**
 * user用户service
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface UserService {
	User findByUsername(String username);
	int add(User user);
	int edit(User user);
	int editPassword(User user);
	int delete(String ids);
	List<User> findList(Map<String, Object> queryMap);
	int getTotal(Map<String, Object> queryMap);
}

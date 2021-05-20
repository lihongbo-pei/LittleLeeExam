package com.littlelee.exam.dao.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * user用户 dao
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface UserDao {
	User findByUsername(String username);
	int add(User user);
	int edit(User user);
	int editPassword(User user);
	int delete(String ids);
	List<User> findList(Map<String, Object> queryMap);
	int getTotal(Map<String, Object> queryMap);
}

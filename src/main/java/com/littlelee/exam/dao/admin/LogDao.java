package com.littlelee.exam.dao.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * ϵͳ��־��dao
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface LogDao {
	int add(Log log);
	List<Log> findList(Map<String, Object> queryMap);
	int getTotal(Map<String, Object> queryMap);
	int delete(String ids);
}

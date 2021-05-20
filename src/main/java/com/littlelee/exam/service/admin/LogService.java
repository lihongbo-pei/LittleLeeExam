package com.littlelee.exam.service.admin;

import java.util.List;
import java.util.Map;

import com.littlelee.exam.entity.admin.Log;
import org.springframework.stereotype.Service;


/**
 * 日志管理service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface LogService {
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}

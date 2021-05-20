package com.littlelee.exam.service.admin;

import java.util.List;

import com.littlelee.exam.entity.admin.Authority;
import org.springframework.stereotype.Service;


/**
 * 权限service接口
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface AuthorityService {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}

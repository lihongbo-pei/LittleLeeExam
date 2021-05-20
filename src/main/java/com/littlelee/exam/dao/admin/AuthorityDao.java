package com.littlelee.exam.dao.admin;

import java.util.List;

import com.littlelee.exam.entity.admin.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * Ȩ��ʵ����dao
 * @author llq
 *
 */
//@Repository
@Mapper
public interface AuthorityDao {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}

package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学科专业dao层
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface SubjectDao {

    int add(Subject subject);
    int edit(Subject subject);
    List<Subject> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    Subject findById(Long id);

}

package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生dao层
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface StudentDao {

    int add(Student student);
    int edit(Student student);
    List<Student> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    Student findByName(String name);
}

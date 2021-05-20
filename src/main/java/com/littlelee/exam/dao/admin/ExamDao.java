package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 考试dao层
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface ExamDao {

    int add(Exam exam);
    int edit(Exam exam);
    List<Exam> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    List<Exam> findListByUser(Map<String, Object> queryMap);
    Integer getTotalByUser(Map<String, Object> queryMap);
    Exam findById(Long id);
    int updateExam(Exam exam);
}

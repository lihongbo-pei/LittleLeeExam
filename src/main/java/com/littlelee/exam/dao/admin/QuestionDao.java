package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 试题dao层
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface QuestionDao {

    int add(Question question);
    int edit(Question question);
    List<Question> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    Question findByTitle(String title);
    int getQuestionNumByType(Map<String, Long> queryMap);
    Question findById(Long id);
}

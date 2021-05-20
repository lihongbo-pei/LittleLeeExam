package com.littlelee.exam.service.admin;

import com.littlelee.exam.entity.admin.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试题service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface QuestionService {

    int add(Question question);
    int edit(Question question);
    List<Question> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    Question findByTitle(String title);
    int getQuestionNumByType(Map<String, Long> queryMap);
    Question findById(Long id);

}

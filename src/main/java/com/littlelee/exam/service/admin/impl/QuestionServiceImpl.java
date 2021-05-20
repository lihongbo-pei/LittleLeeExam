package com.littlelee.exam.service.admin.impl;

import com.littlelee.exam.dao.admin.QuestionDao;
import com.littlelee.exam.entity.admin.Question;
import com.littlelee.exam.service.admin.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试题service实现类
 * @author littlelee
 * @date 2020/3/2 9:34
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public int add(Question question) {
        return questionDao.add(question);
    }

    @Override
    public int edit(Question question) {
        return questionDao.edit(question);
    }

    @Override
    public List<Question> findList(Map<String, Object> queryMap) {
        return questionDao.findList(queryMap);
    }

    @Override
    public int delete(Long id) {
        return questionDao.delete(id);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return questionDao.getTotal(queryMap);
    }

    @Override
    public Question findByTitle(String title) {
        return questionDao.findByTitle(title);
    }

    @Override
    public int getQuestionNumByType(Map<String, Long> queryMap) {
        return questionDao.getQuestionNumByType(queryMap);
    }

    @Override
    public Question findById(Long id) {
        return questionDao.findById(id);
    }


}

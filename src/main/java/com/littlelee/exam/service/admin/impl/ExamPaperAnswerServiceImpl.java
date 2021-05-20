package com.littlelee.exam.service.admin.impl;

import com.littlelee.exam.dao.admin.ExamPaperAnswerDao;
import com.littlelee.exam.entity.admin.ExamPaperAnswer;
import com.littlelee.exam.service.admin.ExamPaperAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试卷答题service实现类
 * @author littlelee
 * @date 2020/3/2 9:34
 */
@Service
public class ExamPaperAnswerServiceImpl implements ExamPaperAnswerService {

    @Autowired
    private ExamPaperAnswerDao examPaperAnswerDao;


    @Override
    public int add(ExamPaperAnswer examPaperAnswer) {
        return examPaperAnswerDao.add(examPaperAnswer);
    }

    @Override
    public int edit(ExamPaperAnswer examPaperAnswer) {
        return examPaperAnswerDao.edit(examPaperAnswer);
    }

    @Override
    public List<ExamPaperAnswer> findList(Map<String, Object> queryMap) {
        return examPaperAnswerDao.findList(queryMap);
    }

    @Override
    public int delete(Long id) {
        return examPaperAnswerDao.delete(id);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return examPaperAnswerDao.getTotal(queryMap);
    }

    @Override
    public List<ExamPaperAnswer> findListByUser(Map<String, Object> queryMap) {
        return examPaperAnswerDao.findListByUser(queryMap);
    }

    @Override
    public int submitAnswer(ExamPaperAnswer examPaperAnswer) {
        return examPaperAnswerDao.submitAnswer(examPaperAnswer);
    }
}

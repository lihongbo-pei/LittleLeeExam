package com.littlelee.exam.service.admin.impl;

import com.littlelee.exam.dao.admin.ExamDao;
import com.littlelee.exam.entity.admin.Exam;
import com.littlelee.exam.service.admin.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 考试service实现类
 * @author littlelee
 * @date 2020/3/2 9:34
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;

    @Override
    public int add(Exam exam) {
        return examDao.add(exam);
    }

    @Override
    public int edit(Exam exam) {
        return examDao.edit(exam);
    }

    @Override
    public List<Exam> findList(Map<String, Object> queryMap) {
        return examDao.findList(queryMap);
    }

    @Override
    public int delete(Long id) {
        return examDao.delete(id);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return examDao.getTotal(queryMap);
    }

    @Override
    public List<Exam> findListByUser(Map<String, Object> queryMap) {
        return examDao.findListByUser(queryMap);
    }

    @Override
    public Integer getTotalByUser(Map<String, Object> queryMap) {
        return examDao.getTotalByUser(queryMap);
    }

    @Override
    public Exam findById(Long id) {
        return examDao.findById(id);
    }

    @Override
    public int updateExam(Exam exam) {
        return examDao.updateExam(exam);
    }

}

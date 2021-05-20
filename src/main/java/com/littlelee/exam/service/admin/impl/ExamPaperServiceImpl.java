package com.littlelee.exam.service.admin.impl;

import com.littlelee.exam.dao.admin.ExamPaperDao;
import com.littlelee.exam.entity.admin.ExamPaper;
import com.littlelee.exam.service.admin.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试卷service实现类
 * @author littlelee
 * @date 2020/3/2 9:34
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperDao examPaperDao;


    @Override
    public int add(ExamPaper examPaper) {
        return examPaperDao.add(examPaper);
    }

    @Override
    public int edit(ExamPaper examPaper) {
        return examPaperDao.edit(examPaper);
    }

    @Override
    public List<ExamPaper> findList(Map<String, Object> queryMap) {
        return examPaperDao.findList(queryMap);
    }

    @Override
    public int delete(Long id) {
        return examPaperDao.delete(id);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return examPaperDao.getTotal(queryMap);
    }

    @Override
    public List<ExamPaper> findHistory(Map<String, Object> queryMap) {
        return examPaperDao.findHistory(queryMap);
    }

    @Override
    public Integer getHistoryTotal(Map<String, Object> queryMap) {
        return examPaperDao.getHistoryTotal(queryMap);
    }

    @Override
    public ExamPaper find(Map<String, Object> queryMap) {
        return examPaperDao.find(queryMap);
    }

    @Override
    public int submitPaper(ExamPaper examPaper) {
        return examPaperDao.submitPaper(examPaper);
    }

    @Override
    public List<Map<String, Object>> getExamStats(Long examId) {
        return examPaperDao.getExamStats(examId);
    }

}

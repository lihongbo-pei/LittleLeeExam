package com.littlelee.exam.service.admin.impl;

import com.littlelee.exam.dao.admin.SubjectDao;
import com.littlelee.exam.entity.admin.Subject;
import com.littlelee.exam.service.admin.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学科专业service实现类
 * @author littlelee
 * @date 2020/3/2 9:34
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public int add(Subject subject) {
        return subjectDao.add(subject);
    }

    @Override
    public int edit(Subject subject) {
        return subjectDao.edit(subject);
    }

    @Override
    public List<Subject> findList(Map<String, Object> queryMap) {
        return subjectDao.findList(queryMap);
    }

    @Override
    public int delete(Long id) {
        return subjectDao.delete(id);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return subjectDao.getTotal(queryMap);
    }

    @Override
    public Subject findById(Long id) {
        return subjectDao.findById(id);
    }
}

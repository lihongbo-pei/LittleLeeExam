package com.littlelee.exam.service.admin;

import com.littlelee.exam.entity.admin.Exam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 考试service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface ExamService {

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

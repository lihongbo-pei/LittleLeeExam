package com.littlelee.exam.service.admin;

import com.littlelee.exam.entity.admin.ExamPaperAnswer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试卷答题service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface ExamPaperAnswerService {

    int add(ExamPaperAnswer examPaperAnswer);
    int edit(ExamPaperAnswer examPaperAnswer);
    List<ExamPaperAnswer> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    List<ExamPaperAnswer> findListByUser(Map<String, Object> queryMap);
    int submitAnswer(ExamPaperAnswer examPaperAnswer);

}

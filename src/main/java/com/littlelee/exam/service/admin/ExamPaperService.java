package com.littlelee.exam.service.admin;

import com.littlelee.exam.entity.admin.ExamPaper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 试卷service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface ExamPaperService {

    int add(ExamPaper examPaper);
    int edit(ExamPaper examPaper);
    List<ExamPaper> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    List<ExamPaper> findHistory(Map<String, Object> queryMap);
    Integer getHistoryTotal(Map<String, Object> queryMap);
    ExamPaper find(Map<String, Object> queryMap);
    int submitPaper(ExamPaper examPaper);
    List<Map<String,Object>> getExamStats(Long examId);

}

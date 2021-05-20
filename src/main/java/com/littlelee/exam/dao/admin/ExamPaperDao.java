package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.ExamPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 试卷dao层
 * @author littlelee
 * @date 2020/3/1 22:46
 */
//@Repository
@Mapper
public interface ExamPaperDao {

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

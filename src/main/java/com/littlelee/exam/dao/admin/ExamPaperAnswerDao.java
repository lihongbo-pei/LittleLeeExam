package com.littlelee.exam.dao.admin;

import com.littlelee.exam.entity.admin.ExamPaperAnswer;
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
public interface ExamPaperAnswerDao {

    int add(ExamPaperAnswer examPaperAnswer);
    int edit(ExamPaperAnswer examPaperAnswer);
    List<ExamPaperAnswer> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    List<ExamPaperAnswer> findListByUser(Map<String, Object> queryMap);
    int submitAnswer(ExamPaperAnswer examPaperAnswer);
}

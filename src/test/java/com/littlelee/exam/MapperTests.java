package com.littlelee.exam;

import com.littlelee.exam.dao.admin.ExamDao;
import com.littlelee.exam.dao.admin.UserDao;
import com.littlelee.exam.entity.admin.Exam;
import com.littlelee.exam.entity.admin.ExamPaper;
import com.littlelee.exam.entity.admin.User;
import com.littlelee.exam.service.admin.ExamPaperService;
import com.littlelee.exam.util.DateFormatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author littlelee
 * @date 2021/4/7 17:42
 */
@SpringBootTest
public class MapperTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ExamDao examDao;

    @Autowired
    private ExamPaperService examPaperService;

    @Test
    public void selectUser(){
        User user = userDao.findByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void selectExam(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("subjectId",8);
        queryMap.put("startTime", DateFormatUtil.getDate("yyyy-MM-dd hh:mm:ss",new Date()));
        queryMap.put("endTime", DateFormatUtil.getDate("yyyy-MM-dd hh:mm:ss",new Date()));
        queryMap.put("offset",0);
        queryMap.put("pageSize",10);
        List<Exam> list = examDao.findListByUser(queryMap);
    }

    @Test
    public void selectExamPaper(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("studentId",19);
        queryMap.put("startTime", DateFormatUtil.getDate("yyyy-MM-dd hh:mm:ss",new Date()));
        queryMap.put("endTime", DateFormatUtil.getDate("yyyy-MM-dd hh:mm:ss",new Date()));
        queryMap.put("offset",0);
        queryMap.put("pageSize",10);
        List<ExamPaper> history = examPaperService.findHistory(queryMap);
        for (ExamPaper examPaper : history) {
            System.out.println(examPaper);
        }
    }

}

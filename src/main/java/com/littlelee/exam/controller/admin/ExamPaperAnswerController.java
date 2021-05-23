package com.littlelee.exam.controller.admin;

import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.ExamPaperAnswerService;
import com.littlelee.exam.service.admin.ExamService;
import com.littlelee.exam.service.admin.QuestionService;
import com.littlelee.exam.service.admin.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 试卷答题管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/examPaperAnswer")
@Controller
public class ExamPaperAnswerController {

    @Autowired
    private ExamPaperAnswerService examPaperAnswerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;

    /**
     * 试卷答题列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        model.addObject("examList", examService.findList(queryMap));
        model.addObject("studentList",studentService.findList(queryMap));
        model.addObject("questionList",questionService.findList(queryMap));
        model.setViewName("examPaperAnswer/list");
        return model;
    }

    /**
     * 模糊搜索分页显示列表查询
     * @param studentId
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(name = "examId",required = false) Long examId,
            @RequestParam(name = "studentId",required = false) Long studentId,
            @RequestParam(name = "questionId",required = false) Long questionId,
            Page page
    ){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        if (examId != null){
            queryMap.put("examId", examId);
        }
        if (studentId != null){
            queryMap.put("studentId", studentId);
        }
        if (questionId != null){
            queryMap.put("questionId", questionId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",examPaperAnswerService.findList(queryMap));
        result.put("total",examPaperAnswerService.getTotal(queryMap));
        return result;
    }


}

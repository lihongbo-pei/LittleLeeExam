package com.littlelee.exam.controller.admin;

import com.littlelee.exam.entity.admin.Exam;
import com.littlelee.exam.entity.admin.Question;
import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.ExamService;
import com.littlelee.exam.service.admin.QuestionService;
import com.littlelee.exam.service.admin.SubjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 考试管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/exam")
@Controller
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 考试列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        model.addObject("subjectList",subjectService.findList(queryMap));
        model.addObject("singleScore", Question.QUESTION_TYPE_SINGLE_SCORE);
        model.addObject("muiltScore",Question.QUESTION_TYPE_MUILT_SCORE);
        model.addObject("chargeScore",Question.QUESTION_TYPE_CHARGE_SCORE);
        model.setViewName("exam/list");
        return model;
    }

    /**
     * 模糊搜索分页显示列表查询
     * @param name
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "subjectId",required = false) Long subjectId,
            @RequestParam(name = "startTime",required = false) String startTime,
            @RequestParam(name = "endTime",required = false) String endTime,
            Page page
    ){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", name);
        if (subjectId != null){
            queryMap.put("subjectId",subjectId);
        }
        if (!StringUtils.isEmpty(startTime)){
            queryMap.put("startTime",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryMap.put("endTime",endTime);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",examService.findList(queryMap));
        result.put("total",examService.getTotal(queryMap));
        return result;
    }
    /**
     * 添加考试
     * @param exam
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Exam exam){
        Map<String,String> result = new HashMap<String, String>();
        if (exam == null){
            result.put("type","error");
            result.put("msg","请填写正确的考试信息!");
            return result;
        }
        if (StringUtils.isEmpty(exam.getName())){
            result.put("type","error");
            result.put("msg","请填写考试名称!");
            return result;
        }
        if (exam.getSubjectId() == null){
            result.put("type","error");
            result.put("msg","请选择所属科目!");
            return result;
        }
        if (exam.getStartTime() == null){
            result.put("type","error");
            result.put("msg","请填写考试开始时间!");
            return result;
        }
        if (exam.getEndTime() == null){
            result.put("type","error");
            result.put("msg","请填写考试结束时间!");
            return result;
        }
        if (exam.getPassScore() == 0){
            result.put("type","error");
            result.put("msg","请填写考试及格分数!");
            return result;
        }
        if (exam.getSingleQuestionNum() == 0 && exam.getMuiltQuestionNum() == 0 && exam.getChargeQuestionNum() == 0){
            result.put("type","error");
            result.put("msg","至少填写一种题型数量!");
            return result;
        }
        exam.setCreateTime(new Date());
        //查询所填写的题型数量是否满足
        //获取单选题总数
        Map<String,Long> queryMap = new HashMap<>();
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_SINGLE);
        queryMap.put("subjectId", exam.getSubjectId());
        int singleQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getSingleQuestionNum() > singleQuestionTotalNum){
            result.put("type","error");
            result.put("msg","单选题数量超过题库单选题总数，请修改!");
            return result;
        }
        //多选
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_MUILT);
        int muiltQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getMuiltQuestionNum() > muiltQuestionTotalNum){
            result.put("type","error");
            result.put("msg","多选题数量超过题库多选题总数，请修改!");
            return result;
        }
        //判断
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_CHARGE);
        int chargeQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getChargeQuestionNum() > chargeQuestionTotalNum){
            result.put("type","error");
            result.put("msg","判断题数量超过题库判断题总数，请修改!");
            return result;
        }
        exam.setQuestionNum(exam.getSingleQuestionNum()+exam.getMuiltQuestionNum()+exam.getChargeQuestionNum());
        exam.setTotalScore(exam.getSingleQuestionNum() * Question.QUESTION_TYPE_SINGLE_SCORE + exam.getMuiltQuestionNum() * Question.QUESTION_TYPE_MUILT_SCORE + exam.getChargeQuestionNum() * Question.QUESTION_TYPE_CHARGE_SCORE);
        if (examService.add(exam) <= 0){
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","添加成功!");
        return result;
    }

    /**
     * 编辑考试
     * @param exam
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Exam exam){
        Map<String,String> result = new HashMap<String, String>();
        if (exam == null){
            result.put("type","error");
            result.put("msg","请选择正确的考试信息进行编辑!");
            return result;
        }
        if (StringUtils.isEmpty(exam.getName())){
            result.put("type","error");
            result.put("msg","请填写考试名称!");
            return result;
        }
        if (exam.getSubjectId() == null){
            result.put("type","error");
            result.put("msg","请选择所属科目!");
            return result;
        }
        if (exam.getStartTime() == null){
            result.put("type","error");
            result.put("msg","请填写考试开始时间!");
            return result;
        }
        if (exam.getEndTime() == null){
            result.put("type","error");
            result.put("msg","请填写考试结束时间!");
            return result;
        }
        if (exam.getPassScore() == 0){
            result.put("type","error");
            result.put("msg","请填写考试及格分数!");
            return result;
        }
        if (exam.getSingleQuestionNum() == 0 && exam.getMuiltQuestionNum() == 0 && exam.getChargeQuestionNum() == 0){
            result.put("type","error");
            result.put("msg","至少填写一种题型数量!");
            return result;
        }
        //查询所填写的题型数量是否满足
        //获取单选题总数
        Map<String,Long> queryMap = new HashMap<>();
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_SINGLE);
        queryMap.put("subjectId", exam.getSubjectId());
        int singleQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getSingleQuestionNum() > singleQuestionTotalNum){
            result.put("type","error");
            result.put("msg","单选题数量超过题库单选题总数，请修改!");
            return result;
        }
        //多选
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_MUILT);
        int muiltQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getMuiltQuestionNum() > muiltQuestionTotalNum){
            result.put("type","error");
            result.put("msg","多选题数量超过题库多选题总数，请修改!");
            return result;
        }
        //判断
        queryMap.put("questionType", (long) Question.QUESTION_TYPE_CHARGE);
        int chargeQuestionTotalNum = questionService.getQuestionNumByType(queryMap);
        if (exam.getChargeQuestionNum() > chargeQuestionTotalNum){
            result.put("type","error");
            result.put("msg","判断题数量超过题库判断题总数，请修改!");
            return result;
        }
        exam.setQuestionNum(exam.getSingleQuestionNum()+exam.getMuiltQuestionNum()+exam.getChargeQuestionNum());
        exam.setTotalScore(exam.getSingleQuestionNum() * Question.QUESTION_TYPE_SINGLE_SCORE + exam.getMuiltQuestionNum() * Question.QUESTION_TYPE_MUILT_SCORE + exam.getChargeQuestionNum() * Question.QUESTION_TYPE_CHARGE_SCORE);

        if (examService.edit(exam) <= 0){
            result.put("type","error");
            result.put("msg","编辑失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","编辑成功!");
        return result;
    }

    /**
     * 删除考试
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(Long id){
        Map<String,String> result = new HashMap<String, String>();
        if (id == null){
            result.put("type","error");
            result.put("msg","请选择要选择要删除的数据!");
            return result;
        }
        try {
            if (examService.delete(id) <= 0){
                result.put("type","error");
                result.put("msg","删除失败，请联系管理员!");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该考试下存在试卷或考试记录信息，不能删除!");
            return result;
        }
        result.put("type","success");
        result.put("msg","删除成功成功!");
        return result;
    }

}

package com.littlelee.exam.controller.admin;

import com.littlelee.exam.entity.admin.ExamPaper;
import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.ExamPaperService;
import com.littlelee.exam.service.admin.ExamService;
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
 * 试卷管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/examPaper")
@Controller
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;

    /**
     * 试卷列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        model.addObject("examList",examService.findList(queryMap));
        model.addObject("studentList",studentService.findList(queryMap));
        model.setViewName("examPaper/list");
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
            @RequestParam(name = "studentId",required = false) String studentId,
            @RequestParam(name = "status",required = false) Integer status,
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
        if (status != null){
            queryMap.put("status", status);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",examPaperService.findList(queryMap));
        result.put("total",examPaperService.getTotal(queryMap));
        return result;
    }
    /**
     * 添加试卷
     * @param examPaper
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(ExamPaper examPaper){
        Map<String,String> result = new HashMap<String, String>();
        if (examPaper == null){
            result.put("type","error");
            result.put("msg","请填写正确的试卷信息!");
            return result;
        }
        if (examPaper.getExamId() == null){
            result.put("type","error");
            result.put("msg","请选择试卷所属考试!");
            return result;
        }
        if (examPaper.getStudentId() == null){
            result.put("type","error");
            result.put("msg","请选择所属学生!");
            return result;
        }
        if (examPaperService.add(examPaper) <= 0){
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","添加成功!");
        return result;
    }

    /**
     * 编辑试卷
     * @param examPaper
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(ExamPaper examPaper){
        Map<String,String> result = new HashMap<String, String>();
        if (examPaper == null){
            result.put("type","error");
            result.put("msg","请选择正确的试卷信息进行编辑!");
            return result;
        }
        if (examPaper == null){
            result.put("type","error");
            result.put("msg","请填写正确的试卷信息!");
            return result;
        }
        if (examPaper.getExamId() == null){
            result.put("type","error");
            result.put("msg","请选择试卷所属考试!");
            return result;
        }
        if (examPaper.getStudentId() == null){
            result.put("type","error");
            result.put("msg","请选择所属学生!");
            return result;
        }
        if (examPaperService.edit(examPaper) <= 0){
            result.put("type","error");
            result.put("msg","编辑失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","编辑成功!");
        return result;
    }

    /**
     * 删除试卷
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
            if (examPaperService.delete(id) <= 0){
                result.put("type","error");
                result.put("msg","删除失败，请联系管理员!");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该试卷下存在答题信息，不能删除!");
            return result;
        }
        result.put("type","success");
        result.put("msg","删除成功成功!");
        return result;
    }

}

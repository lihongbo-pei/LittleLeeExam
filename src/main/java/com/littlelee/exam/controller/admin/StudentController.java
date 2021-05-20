package com.littlelee.exam.controller.admin;

import com.littlelee.exam.entity.admin.Student;
import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.StudentService;
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
 * 考生管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 考生列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        model.addObject("subjectList",subjectService.findList(queryMap));
        model.setViewName("student/list");
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
            Page page
    ){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name",name);
        if (subjectId != null){
            queryMap.put("subjected",subjectId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",studentService.findList(queryMap));
        result.put("total",studentService.getTotal(queryMap));
        return result;
    }
    /**
     * 添加考生
     * @param student
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Student student){
        Map<String,String> result = new HashMap<String, String>();
        if (student == null){
            result.put("type","error");
            result.put("msg","请填写正确的考生信息!");
            return result;
        }
        if (StringUtils.isEmpty(student.getName())){
            result.put("type","error");
            result.put("msg","请填写考生用户名!");
            return result;
        }
        if (StringUtils.isEmpty(student.getPassword())){
            result.put("type","error");
            result.put("msg","请填写考生密码!");
            return result;
        }
        if (student.getSubjectId() == null){
            result.put("type","error");
            result.put("msg","请选择考生所属学科专业!");
            return result;
        }
        student.setCreateTime(new Date());
        //添加之前判断登录名是否存在
        if (isExistName(student.getName(),-1L)){
            result.put("type","error");
            result.put("msg","该登录账号已经存在!");
            return result;
        }
        if (studentService.add(student) <= 0){
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","添加成功!");
        return result;
    }

    /**
     * 编辑考生
     * @param student
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Student student){
        Map<String,String> result = new HashMap<String, String>();
        if (student == null){
            result.put("type","error");
            result.put("msg","请填写正确的学科信息!");
            return result;
        }
        if (StringUtils.isEmpty(student.getName())){
            result.put("type","error");
            result.put("msg","请填写考生用户名!");
            return result;
        }
        if (StringUtils.isEmpty(student.getPassword())){
            result.put("type","error");
            result.put("msg","请填写考生密码!");
            return result;
        }
        if (student.getSubjectId() == null){
            result.put("type","error");
            result.put("msg","请选择考生所属学科专业!");
            return result;
        }
        //编辑之前判断登录名是否存在
        if (isExistName(student.getName(),student.getId())){
            result.put("type","error");
            result.put("msg","该登录账号已经存在!");
            return result;
        }
        if (studentService.edit(student) <= 0){
            result.put("type","error");
            result.put("msg","编辑失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","编辑成功!");
        return result;
    }

    /**
     * 删除考生
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
            if (studentService.delete(id) <= 0){
                result.put("type","error");
                result.put("msg","删除失败，请联系管理员!");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该考生下存在考试信息，不能删除!");
            return result;
        }
        result.put("type","success");
        result.put("msg","删除成功成功!");
        return result;
    }

    /**
     * 判断用户名是否存在
     * @param name
     * @param id
     * @return
     */
    private boolean isExistName(String name,Long id){
        Student student = studentService.findByName(name);
        if (student == null){
            return false;
        }
        if (student.getId().longValue() == id.longValue()){
            return false;
        }
        return true;
    }
}

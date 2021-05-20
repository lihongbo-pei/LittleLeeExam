package com.littlelee.exam.controller.admin;

import com.littlelee.exam.entity.admin.Menu;
import com.littlelee.exam.entity.admin.Subject;
import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.SubjectService;
import com.littlelee.exam.util.MenuUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学科专业管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/subject")
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 学科专业列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model, HttpServletRequest request){
        model.setViewName("subject/list");
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
            Page page
    ){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name",name);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",subjectService.findList(queryMap));
        result.put("total",subjectService.getTotal(queryMap));
        return result;
    }
    /**
     * 添加学科专业
     * @param subject
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Subject subject){
        Map<String,String> result = new HashMap<String, String>();
        if (subject == null){
            result.put("type","error");
            result.put("msg","请填写正确的学科信息!");
            return result;
        }
        if (StringUtils.isEmpty(subject.getName())){
            result.put("type","error");
            result.put("msg","请填写学科名称!");
            return result;
        }
        if (subjectService.add(subject) <= 0){
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","添加成功!");
        return result;
    }

    /**
     * 编辑学科专业
     * @param subject
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Subject subject){
        Map<String,String> result = new HashMap<String, String>();
        if (subject == null){
            result.put("type","error");
            result.put("msg","请填写正确的学科信息!");
            return result;
        }
        if (StringUtils.isEmpty(subject.getName())){
            result.put("type","error");
            result.put("msg","请填写学科名称!");
            return result;
        }
        if (subjectService.edit(subject) <= 0){
            result.put("type","error");
            result.put("msg","编辑失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","编辑成功!");
        return result;
    }

    /**
     * 删除学科专业
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
            if (subjectService.delete(id) <= 0){
                result.put("type","error");
                result.put("msg","删除失败，请联系管理员!");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该学科下存在考生，不能删除!");
            return result;
        }
        result.put("type","success");
        result.put("msg","删除成功成功!");
        return result;
    }
}

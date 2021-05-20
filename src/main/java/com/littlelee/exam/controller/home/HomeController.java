package com.littlelee.exam.controller.home;


import com.littlelee.exam.entity.admin.Student;
import com.littlelee.exam.service.admin.StudentService;
import com.littlelee.exam.service.admin.SubjectService;
import com.littlelee.exam.util.CpachaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台主页控制器
 * @author littlelee
 * @date 2020/3/26 19:04
 */

@RequestMapping("/home/")
@Controller
public class HomeController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    /**
     * 前台首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model){
        return model;
    }

    /**
     * 前台用户登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model){
        model.addObject("title","用户登录");
        model.setViewName("home/login");
        return model;
    }

    /**
     * 前台用户注册
     * @param model
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(ModelAndView model){
        model.addObject("title","用户注册");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        model.addObject("subjectList", subjectService.findList(queryMap));
        model.setViewName("home/register");
        return model;
    }

    /**
     * 用户注册提交
     * @param student
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> register(Student student, String cpacha, HttpServletRequest request){
        Map<String,String> result = new HashMap<>();
        if (student == null){
            result.put("type","error");
            result.put("msg","请填写正确考生信息");
            return result;
        }
        if (StringUtils.isEmpty(student.getName())){
            result.put("type","error");
            result.put("msg","请填写考生登录名！");
            return result;
        }
        if (StringUtils.isEmpty(student.getPassword())){
            result.put("type","error");
            result.put("msg","请填写考生登录密码！");
            return result;
        }
        if (student.getSubjectId() == null){
            result.put("type","error");
            result.put("msg","请选择所属学科专业！");
            return result;
        }
        //验证码
        Object registerCpacha = request.getSession().getAttribute("registerCpacha");
        if(registerCpacha == null){
            result.put("type", "error");
            result.put("msg", "会话超时，请刷新页面！");
            return result;
        }
        if(!cpacha.toUpperCase().equals(registerCpacha.toString().toUpperCase())){
            result.put("type", "error");
            result.put("msg", "验证码错误！");
            return result;
        }
        Student existStudent = studentService.findByName(student.getName());
        if (existStudent != null){
            result.put("type","error");
            result.put("msg","该用户名已存在！");
            return result;
        }
        student.setCreateTime(new Date());
        try {
            if (studentService.add(student) <= 0){
                result.put("type","error");
                result.put("msg","注册失败，请联系管理员！");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","数据库连接异常，请检查数据库连接！");
            return result;
        }
        result.put("type","success");
        result.put("msg","注册成功！");
        return result;
    }

    /**
     * 用户登录提交
     * @param student
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(Student student, HttpServletRequest request){
        Map<String,String> result = new HashMap<>();
        if (student == null){
            result.put("type","error");
            result.put("msg","请填写正确考生信息");
            return result;
        }
        if (StringUtils.isEmpty(student.getName())){
            result.put("type","error");
            result.put("msg","请填写考生登录名！");
            return result;
        }
        if (StringUtils.isEmpty(student.getPassword())){
            result.put("type","error");
            result.put("msg","请填写考生登录密码！");
            return result;
        }
        Student existStudent = studentService.findByName(student.getName());
        if (existStudent == null){
            result.put("type","error");
            result.put("msg","该用户名不存在！");
            return result;
        }
        if (!student.getPassword().equals(existStudent.getPassword())){
            result.put("type","error");
            result.put("msg","密码错误！");
            return result;
        }
        request.getSession().setAttribute("student",existStudent);
        result.put("type","success");
        result.put("msg","登录成功！");
        return result;
    }

    /**
     * 本系统所有的验证码均采用此方法
     * @param vcodeLen
     * @param width
     * @param height
     * @param cpachaType:用来区别验证码的类型，传入字符串
     * @param request
     * @param response
     */
    @RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
            @RequestParam(name="w",required=false,defaultValue="100") Integer width,
            @RequestParam(name="h",required=false,defaultValue="30") Integer height,
            @RequestParam(name="type",required=true,defaultValue="registerCpacha") String cpachaType,
            HttpServletRequest request,
            HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(cpachaType, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

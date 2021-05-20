package com.littlelee.exam.controller.admin;

import com.littlelee.exam.entity.admin.Question;
import com.littlelee.exam.page.admin.Page;
import com.littlelee.exam.service.admin.QuestionService;
import com.littlelee.exam.service.admin.SubjectService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 试题管理后台控制器
 * @author littlelee
 * @date 2020/3/1 22:19
 */
@RequestMapping("/admin/question")
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 试题列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 99999);
        model.addObject("subjectList", subjectService.findList(queryMap));
        model.setViewName("views/question/list");
        return model;
    }

    /**
     * 模糊搜索分页显示列表查询
     * @param title
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "questionType",required = false) Integer questionType,
            @RequestParam(name="subjectId",required=false) Long subjectId,
            Page page
    ){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("title", title);
        if (questionType != null){
            queryMap.put("questionType",questionType);
        }
        if(subjectId != null){
            queryMap.put("subjectId", subjectId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        result.put("rows",questionService.findList(queryMap));
        result.put("total",questionService.getTotal(queryMap));
        return result;
    }
    /**
     * 添加试题
     * @param question
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Question question){
        Map<String,String> result = new HashMap<String, String>();
        if (question == null){
            result.put("type","error");
            result.put("msg","请填写正确的试题信息!");
            return result;
        }
        if (StringUtils.isEmpty(question.getTitle())){
            result.put("type","error");
            result.put("msg","请填写试题题目!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAnswer())){
            result.put("type","error");
            result.put("msg","请填写试题正确答案!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAttrA())){
            result.put("type","error");
            result.put("msg","请填写试题选项A!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAttrB())){
            result.put("type","error");
            result.put("msg","请填写试题选项B!");
            return result;
        }
        question.setCreateTime(new Date());
        question.setScoreByType();
        if (questionService.add(question) <= 0){
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","添加成功!");
        return result;
    }

    /**
     * 编辑试题
     * @param question
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Question question){
        Map<String,String> result = new HashMap<>();
        if (question == null){
            result.put("type","error");
            result.put("msg","请填写正确的试题信息!");
            return result;
        }
        if (StringUtils.isEmpty(question.getTitle())){
            result.put("type","error");
            result.put("msg","请填写试题题目!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAnswer())){
            result.put("type","error");
            result.put("msg","请填写试题正确答案!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAttrA())){
            result.put("type","error");
            result.put("msg","请填写试题选项A!");
            return result;
        }
        if (StringUtils.isEmpty(question.getAttrB())){
            result.put("type","error");
            result.put("msg","请填写试题选项B!");
            return result;
        }
        question.setCreateTime(new Date());
        question.setScoreByType();
        if (questionService.edit(question) <= 0){
            result.put("type","error");
            result.put("msg","编辑失败，请联系管理员!");
            return result;
        }
        result.put("type","success");
        result.put("msg","编辑成功!");
        return result;
    }

    /**
     * 删除试题
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
            if (questionService.delete(id) <= 0){
                result.put("type","error");
                result.put("msg","删除失败，请联系管理员!");
                return result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该试题下存在试题信息，不能删除!");
            return result;
        }
        result.put("type","success");
        result.put("msg","删除成功成功!");
        return result;
    }

    /**
     * 上传文件批量导入试题
     * @param excelFile
     * @param subjectId
     * @return
     */
    @RequestMapping(value="upload_file",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadFile(MultipartFile excelFile, Long subjectId){
        Map<String, String> ret = new HashMap<>();
        if(excelFile == null){
            ret.put("type", "error");
            ret.put("msg", "请选择文件!");
            return ret;
        }
        if(subjectId == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属科目!");
            return ret;
        }
        //规定大于5M不让上传
        if(excelFile.getSize() > 5000000){
            ret.put("type", "error");
            ret.put("msg", "文件大小不要超过5M!");
            return ret;
        }
        String suffix = excelFile.getOriginalFilename().substring(excelFile.getOriginalFilename().lastIndexOf(".")+1);
        if(!"xls,xlsx".contains(suffix)){
            ret.put("type", "error");
            ret.put("msg", "请上传最新xls,xlsx格式的文件!");
            return ret;
        }
        String msg = "导入成功";
        try {
            msg = this.readExcel(excelFile.getInputStream(),subjectId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("".equals(msg)){
            msg = "全部导入成功";
        }
        ret.put("type", "success");
        ret.put("msg", msg);
        return ret;
    }

    /**
     *读取excel文件，并插入到数据库
     * @param fileInputStream
     * @param subjectId
     * @return
     */
    private String readExcel(InputStream fileInputStream, Long subjectId){
        String msg = "";
        try {
            //获取一个表格对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
            //去掉表头，从第二行开始读
            if(sheetAt.getLastRowNum() <= 0){
                msg = "该文件为空";
            }
            for(int rowIndex = 1;rowIndex <= sheetAt.getLastRowNum(); rowIndex++){
                Question question = new Question();
                XSSFRow row = sheetAt.getRow(rowIndex);
                if(row.getCell(0) == null){
                    msg += "第" + rowIndex + "行，试题类型为空，跳过<br/>";
                    continue;
                }
                Double numericCellValue = row.getCell(0).getNumericCellValue();
                question.setQuestionType(numericCellValue.intValue());
                if(row.getCell(1) == null){
                    msg += "第" + rowIndex + "行，题目为空，跳过<br/>";
                    continue;
                }
                question.setTitle(row.getCell(1).getStringCellValue());
                if(row.getCell(2) == null){
                    msg += "第" + rowIndex + "行，分值为空，跳过<br/>";
                    continue;
                }
                numericCellValue = row.getCell(2).getNumericCellValue();
                question.setScore(numericCellValue.intValue());
                if(row.getCell(3) == null){
                    msg += "第" + rowIndex + "行，选项A为空，跳过<br/>";
                    continue;
                }
                question.setAttrA(row.getCell(3).getStringCellValue());
                if(row.getCell(4) == null){
                    msg += "第" + rowIndex + "行，选项B为空，跳过<br/>";
                    continue;
                }
                question.setAttrB(row.getCell(4).getStringCellValue());
                question.setAttrC(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());
                question.setAttrD(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());
                if(row.getCell(7) == null){
                    msg += "第" + rowIndex + "行，正确答案为空，跳过\n";
                    continue;
                }
                question.setAnswer(row.getCell(7).getStringCellValue());
                question.setCreateTime(new Date());
                question.setSubjectId(subjectId);
                if(questionService.add(question) <= 0){
                    msg += "第" + rowIndex + "行，插入数据库失败\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}

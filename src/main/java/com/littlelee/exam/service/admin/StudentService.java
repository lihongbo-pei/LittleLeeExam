package com.littlelee.exam.service.admin;

import com.littlelee.exam.entity.admin.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 考生service类
 * @author littlelee
 * @date 2020/3/2 9:31
 */
@Service
public interface StudentService {

    int add(Student student);
    int edit(Student student);
    List<Student> findList(Map<String, Object> queryMap);
    int delete(Long id);
    Integer getTotal(Map<String, Object> queryMap);
    Student findByName(String name);

}

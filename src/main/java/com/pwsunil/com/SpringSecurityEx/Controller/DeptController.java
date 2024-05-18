package com.pwsunil.com.SpringSecurityEx.Controller;

import com.pwsunil.com.SpringSecurityEx.Models.Dept;
import com.pwsunil.com.SpringSecurityEx.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DepartmentRepository repository;
    @PostMapping("/saveDept")
    public void saveDept(){
        Dept dept = new Dept();
        dept.setDeptNo(10);
        dept.setdName("HR");
        dept.setLoc("Florida");
        repository.save(dept);
    }

    @GetMapping("/getAllDepts")
    public List<Dept> getAllDepts(){
        return (List<Dept>) repository.findAll();
    }
}

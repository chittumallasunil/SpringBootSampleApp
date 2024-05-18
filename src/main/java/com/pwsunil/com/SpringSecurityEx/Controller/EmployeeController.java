package com.pwsunil.com.SpringSecurityEx.Controller;

import com.pwsunil.com.SpringSecurityEx.Models.Emp;
import com.pwsunil.com.SpringSecurityEx.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;

    @GetMapping("/getAllEmps")
    public List<Emp> getAllEmps(){
        return (List<Emp>) repository.findAll();
    }
}

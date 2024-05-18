package com.pwsunil.com.SpringSecurityEx.Repository;

import com.pwsunil.com.SpringSecurityEx.Models.Emp;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Emp, Integer> {
}

package com.pwsunil.com.SpringSecurityEx.Repository;

import com.pwsunil.com.SpringSecurityEx.Models.Dept;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Dept,Integer> {
}

package com.imaginnovate.employee.repository;

import com.imaginnovate.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
}

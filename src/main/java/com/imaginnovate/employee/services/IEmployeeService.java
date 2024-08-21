package com.imaginnovate.employee.services;

import com.imaginnovate.employee.dto.EmployeeDto;
import com.imaginnovate.employee.dto.TaxDto;
import com.imaginnovate.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    String addEmployee(EmployeeDto employeeDto);

    TaxDto getById(String token);

}

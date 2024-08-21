package com.imaginnovate.employee.services;

import com.imaginnovate.employee.dto.EmployeeDto;
import com.imaginnovate.employee.dto.TaxDto;
import com.imaginnovate.employee.entity.Employee;
import com.imaginnovate.employee.repository.IEmployeeRepository;
import com.imaginnovate.employee.utill.TokenUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    TokenUtill tokenUtill;
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    //adding an employee and saving to DB
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto);
//        return iEmployeeRepository.save(employee);
        iEmployeeRepository.save(employee);
        String token = tokenUtill.createToken(employee.getId());

        return token;
    }
    //getting the employee with a particular id from the DB and showing
    public TaxDto getById(String token) {
        int id = tokenUtill.decodeToken(token);
        Optional<Employee> employee = iEmployeeRepository.findById(id);
        Employee emp = employee.get();
        double salary = emp.getSalary();
        double tax = 0;
        if (salary > 1000000) {
            tax = (salary-1000000)/5+(500000/10)+(250000/20);
        }
        else if (salary > 500000) {
            tax = (salary-500000)/10+(250000/20);
        }
        else if (salary > 250000) {
            tax = (salary-250000)/20;
        }
        double cess = 0;
        if (tax > 250000) {
            cess = (tax - 250000)/50;
            tax = tax - cess;
        }
        TaxDto taxDto = new TaxDto();
        taxDto.setTax(tax);
        taxDto.setId(emp.getId());
        taxDto.setFName(emp.getFName());
        taxDto.setLName(emp.getLName());
        taxDto.setSalary(emp.getSalary());
        taxDto.setCess(cess);
        return taxDto;
    }

}

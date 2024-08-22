package com.imaginnovate.employee.entity;

import com.imaginnovate.employee.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@Builder

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumbers;
    private double salary;
    private LocalDate startDate;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.fName = employee.fName;
        this.lName = employee.lName;
        this.salary = employee.salary;
        this.email = employee.email;
        this.phoneNumbers = employee.phoneNumbers;
        this.startDate = employee.startDate;
    }

    public Employee(EmployeeDto employeeDto, int id) {
        this.fName = employeeDto.getFName();
        this.lName = employeeDto.getLName();
        this.email = employeeDto.getEmail();
        this.salary = employeeDto.getSalary();
        this.phoneNumbers = employeeDto.getPhoneNumbers();
        this.startDate = employeeDto.getStartDate();
        this.id = id;
    }

    public Employee(EmployeeDto employeeDto) {
        this.fName = employeeDto.getFName();
        this.lName = employeeDto.getLName();
        this.email = employeeDto.getEmail();
        this.salary = employeeDto.getSalary();
        this.phoneNumbers = employeeDto.getPhoneNumbers();
        this.startDate = employeeDto.getStartDate();
    }


}

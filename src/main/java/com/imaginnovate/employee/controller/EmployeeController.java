package com.imaginnovate.employee.controller;

import com.imaginnovate.employee.dto.EmployeeDto;
import com.imaginnovate.employee.dto.ResponseDto;
import com.imaginnovate.employee.entity.Employee;
import com.imaginnovate.employee.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    IEmployeeService iEmployeeService;

    //adding an employee and saving to DB using post mapping and taking the input by RequestBody
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        String token = iEmployeeService.addEmployee(employeeDto);
        ResponseDto responseDto = new ResponseDto("Employee added Successfully", token);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("{token}/tax-deductions")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable String token) {
        ResponseDto responseDTO = new ResponseDto("user with id Shown bellow", iEmployeeService.getById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

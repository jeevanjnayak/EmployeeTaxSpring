package com.imaginnovate.employee.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EmployeeDto {

    @NotEmpty(message = "First name can't be empty")
    private String fName;

    @NotEmpty(message = "Last name can't be empty")
    private String lName;

    @Email
    private String email;

    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$",message = "invalid number")
    private String phoneNumbers;

    @Min(value = 0, message = "salary can't be less than 15000!")
    private double salary;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

}

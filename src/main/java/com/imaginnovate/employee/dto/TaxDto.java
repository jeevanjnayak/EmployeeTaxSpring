package com.imaginnovate.employee.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class TaxDto {
    private int id;

    private String fName;

    private String lName;

    private double salary;

    private double tax;

    private double cess;

}

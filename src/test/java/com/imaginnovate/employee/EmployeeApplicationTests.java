package com.imaginnovate.employee;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.imaginnovate.employee.dto.EmployeeDto;
import com.imaginnovate.employee.dto.TaxDto;
import com.imaginnovate.employee.entity.Employee;
import com.imaginnovate.employee.services.IEmployeeService;
import com.imaginnovate.employee.utill.TokenUtill;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class EmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TokenUtill tokenUtill;

    @MockBean
    private IEmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{

        Employee employee = Employee.builder()
                .fName("Jeevan")
                .lName("Nayak")
                .email("jeevanjnayak@gmail.com")
                .phoneNumbers("7008962343")
                .salary(1000000).
                startDate(LocalDate.ofEpochDay(11 / 12 / 2022))
                .build();
        given(employeeService.addEmployee(any(EmployeeDto.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));


        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())))
                .andExpect(jsonPath("$.phoneNumbers",
                        is(employee.getEmail())))
                .andExpect(jsonPath("$.salary",
                        is(employee.getEmail())))
                .andExpect(jsonPath("$.startDate",
                        is(employee.getEmail())));

    }
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
        int id = 1;
        TaxDto taxDto1 = TaxDto.builder()
                .fName("Jeevan")
                .lName("Nayak")
                .salary(1000000)
                .tax(55000)
                .cess(0)
                .build();
        given(employeeService.getById(tokenUtill.createToken(id))).willReturn(Optional.of(taxDto1));

        ResultActions response = mockMvc.perform(get("/api/employees/{token}/tax-deductions", tokenUtill));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.fName", is(taxDto1.getFName())))
                .andExpect(jsonPath("$.lName", is(taxDto1.getLName())))
                .andExpect(jsonPath("$.salary", is(taxDto1.getSalary())))
                .andExpect(jsonPath("$.tax", is(taxDto1.getTax())))
                .andExpect(jsonPath("$.cess", is(taxDto1.getCess())));

    }
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
        int id = 1;
        TaxDto taxDto1 = TaxDto.builder()
                .fName("Jeevan")
                .lName("Nayak")
                .salary(1000000)
                .tax(55000)
                .cess(0)
                .build();
        given(employeeService.getById(tokenUtill.createToken(id))).willReturn(Optional.of(taxDto1));

        ResultActions response = mockMvc.perform(get("/api/employees/{token}/tax-deductions", tokenUtill));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }
}
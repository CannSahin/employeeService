package com.example.employeeService.webApi.controllers;

import com.example.employeeService.business.EmployeeManager;
import com.example.employeeService.core.entities.Employee;
import com.example.employeeService.core.entities.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @TestConfiguration
    static class ControllerTestConfig {
        @Bean
        @Primary
        public EmployeeManager employeeManager() {
            return Mockito.mock(EmployeeManager.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeManager employeeManager;

    @Autowired
    private ObjectMapper objectMapper;

    private Employees sampleEmployees;
    private Employee sampleEmployee1;

    @BeforeEach
    void setUp() {
        List<Employee> employeeList = new ArrayList<>();
        sampleEmployee1 = new Employee("1", "Luke", "Skywalker", "luke@rebellion.com", "Jedi Knight");
        Employee sampleEmployee2 = new Employee("2", "Leia", "Organa", "leia@rebellion.com", "General");
        employeeList.add(sampleEmployee1);
        employeeList.add(sampleEmployee2);

        sampleEmployees = new Employees();
        sampleEmployees.setEmployeeList(employeeList);
    }

    @Test
    void testGetAllEmployees_ShouldReturnListOfEmployees() throws Exception {
        when(employeeManager.getAllEmployees()).thenReturn(sampleEmployees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Employees.length()", is(2)))
                .andExpect(jsonPath("$.Employees[0].employee_id", is("1")))
                .andExpect(jsonPath("$.Employees[0].first_name", is("Luke")));
    }

    @Test
    void testAddEmployee_ShouldCreateNewEmployee() throws Exception {
        Employee newEmployee = new Employee("3", "Han", "Solo", "han@solo.com", "Smuggler");

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/employees/3"));

        verify(employeeManager).addEmployee(any(Employee.class));
    }
}
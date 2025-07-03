package com.example.employeeService.webApi.controllers;

import com.example.employeeService.business.EmployeeManager;
import com.example.employeeService.core.entities.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping
    public Employees getAllEmployees() {
        return employeeManager.getAllEmployees();
    }
}
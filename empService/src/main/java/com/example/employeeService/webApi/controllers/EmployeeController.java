package com.example.employeeService.webApi.controllers;

import com.example.employeeService.business.EmployeeManager;
import com.example.employeeService.core.entities.Employee;
import com.example.employeeService.core.entities.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping
    public Employees getAllEmployees() {
        return employeeManager.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeManager.addEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getEmployeeId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
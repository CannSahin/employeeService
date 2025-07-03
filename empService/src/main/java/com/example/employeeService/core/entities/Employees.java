package com.example.employeeService.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Employees {

    @JsonProperty("Employees")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
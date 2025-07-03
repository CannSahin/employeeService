package com.example.employeeService.business;

import com.example.employeeService.core.entities.Employee;
import com.example.employeeService.core.entities.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeManager {

    private static Employees list = new Employees();

    static {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Luke", "skywalker", "skywalker@example.com", "Software Engineer"));
        employees.add(new Employee("2", "Alex", "walt", "alex.wlt@example.com", "Finance Manager"));
        employees.add(new Employee("3", "alicia", "mun", "mun@example.com", "UI/UX Designer"));

        list.setEmployeeList(employees);
    }

    public Employees getAllEmployees() {
        return list;
    }
}
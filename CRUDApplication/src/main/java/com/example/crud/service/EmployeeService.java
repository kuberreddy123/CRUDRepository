package com.example.crud.service;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEMployee(EmployeeDto employeeDto);
    Employee  getEmployeeById(int id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
    int deleteEmployee(int id);
}

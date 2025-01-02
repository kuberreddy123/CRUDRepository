package com.example.crud.serviceImpl;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpoyeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    //This method is to save new employee data
    @Override
    public Employee saveEMployee(EmployeeDto employeeDto) {
   Employee employee = Employee.build(employeeDto.getName(),employeeDto.getDepartment(),employeeDto.getEmail(),employeeDto.getMobile(),employeeDto.getNationality());

    return employeeRepository.save(employee);

    }

    //This method is to get employee by Id
    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return id;
    }
}

package com.example.crud.controller;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.exception.EmployeeNotFoundExcepiton;
import com.example.crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Environment environment;



    EmployeeDto employeeDto = new EmployeeDto();



    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<Employee>(employeeService.saveEMployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id) throws EmployeeNotFoundExcepiton {
        try {
            return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("employee not found");
            throw new EmployeeNotFoundExcepiton(e.getMessage());
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws EmployeeNotFoundExcepiton {

try {
    Employee employee = employeeService.getEmployeeById(id);
    String[] env = environment.getActiveProfiles();
    for(String envi : env) {
        System.out.println(envi);
    }
    if (employee == null) {
        throw new EmployeeNotFoundExcepiton("employee with id" + id + "not found");
    }
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("employee with id" + id + "deleted Successfully", HttpStatus.NO_CONTENT);
} catch (Exception e) {
    throw new EmployeeNotFoundExcepiton(e.getMessage());
}
    }
}




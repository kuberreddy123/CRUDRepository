package com.example.crud;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;
import com.example.crud.serviceImpl.EmpoyeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceMockitoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
   private EmpoyeeServiceImpl empoyeeService;

    @Test
    public void testSaveEmployee() {

        EmployeeDto employeeDto = new EmployeeDto("kuber", "Technical", "kuber@gmail.com", "9876543210", "Indian");
        Employee employee = new Employee(1, "kuber", "Technical", "kuber@gmail.com", "9876543210", "Indian");

        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee employeeSved = empoyeeService.saveEMployee(employeeDto);

        Assertions.assertEquals(1,employeeSved.getId());
        Assertions.assertEquals("kuber",employeeSved.getName());
        Assertions.assertEquals("Indian",employeeSved.getNationality());

    }

}

package com.example.crud;

import com.example.crud.controller.EmployeeController;
import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerMockMvcTests {

    @Autowired
    MockMvc mockMvc;

    @Mock
  private   EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
       mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testSaveEmployee() throws Exception{
        EmployeeDto employeeDto = new EmployeeDto("kuber","Tecnical","kuber@gmail.com","9394938484","Inidan");
        Employee employee = new Employee(1,"kuber","Tecnical","kuber@gmail.com","9394938484","Indian");

       when(employeeService.saveEMployee(any(employeeDto.getClass()))).thenReturn(employee);

       mockMvc.perform(post("/emp/save")
               .content(objectMapper.writeValueAsString(employeeDto))
               .contentType("application/json"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("kuber"))
               .andExpect(jsonPath("$.nationality").value("Indian"));

    }

    @Test
    public void saveEmployeeMissingRequiredField() throws Exception{

        EmployeeDto employeeDto = new EmployeeDto(" ","Tecnical","kuber@gmail.com","9394938484","Inidan");

        mockMvc.perform(post("/emp/save")
                        .content(objectMapper.writeValueAsString(employeeDto))
                        .contentType("application/json"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.name").value("Name is required"));

    }

}

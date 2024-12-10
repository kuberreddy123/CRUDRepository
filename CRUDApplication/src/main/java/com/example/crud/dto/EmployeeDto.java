package com.example.crud.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class EmployeeDto {


    @NotNull
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Department is required")
    @NotEmpty
    private String department;

    @Email
    private String email;

    @NotNull(message = "Mobile number Required")
    @Size(min = 10, max=10)
    @Pattern(regexp = "^[0-9]{10}$", message = "mobile number must be 10 digits and only numbers")
    private String mobile;

    @NotEmpty
    @NotNull(message = "Nationality is required")
    private String nationality;

    public EmployeeDto(String name, String department, String email, String mobile, String nationality){
        this.name = name;
        this.department = department;
        this.email = email;
        this.mobile = mobile;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}

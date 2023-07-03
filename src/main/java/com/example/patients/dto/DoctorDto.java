package com.example.patients.dto;

import lombok.Data;


@Data
public class DoctorDto extends BaseDto{

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String Department;
    private String Specialisation;
    private String degree;

}

package com.example.patients.dto;
import lombok.Data;

@Data
public class PatientDto extends BaseDto{

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String age;
    private String address;
    private String contactNumber;
}

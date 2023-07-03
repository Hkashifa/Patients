package com.example.patients.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class DoctorEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String Email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "department")
    private String Department;

    @Column(name = "specialisation")
    private String Specialisation;

    @Column(name = "degree")
    private String degree;
}

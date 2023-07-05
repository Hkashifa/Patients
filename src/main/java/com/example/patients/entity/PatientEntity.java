package com.example.patients.entity;

import com.example.patients.entity.enums.ActiveStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "patient")
//@EqualsAndHashCode(callSuper = false)
public class PatientEntity extends BaseEntity{

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private String age;

    @Column(name = "address")
    private String address;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "activeStatus")
    private ActiveStatus activeStatus = ActiveStatus.ACTIVE;
}

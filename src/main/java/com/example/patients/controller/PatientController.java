package com.example.patients.controller;


import com.example.patients.dto.PatientDto;
import com.example.patients.service.impl.PatientServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final ModelMapper modelMapper;
    private final PatientServiceImpl service;

    public PatientController(ModelMapper modelMapper,PatientServiceImpl service) {

        this.modelMapper = modelMapper;
        this.service = service;

    }
    //this works
    @GetMapping("/patients")
    public List<PatientDto> getAllPatients() {

        return service.getAllPatient();
    }

    @GetMapping("patient/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable(name = "id") Long id) {
        //this works i tested it
        PatientDto patientResponse = service.getPatientById(id);
        return ResponseEntity.ok().body(patientResponse);
    }

    @PostMapping("/patients/new")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {

        PatientDto newPatient = service.createPatient(patientDto);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);

    }


    @PutMapping("patientupdate/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {

        PatientDto newPatient = service.updatePatient(id,patientDto);

        return ResponseEntity.ok().body(newPatient);
    }

    @PutMapping("patientdelete/{id}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable long id, @RequestBody PatientDto patientDto) {


        PatientDto newpatient = service.deletePatient(id,patientDto);

        return ResponseEntity.ok().body(newpatient);
    }
}

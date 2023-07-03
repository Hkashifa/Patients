package com.example.patients.controller;


import com.example.patients.dto.DoctorDto;
import com.example.patients.service.impl.DoctorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    private final ModelMapper modelMapper;
    private final DoctorServiceImpl doctorService;

    public DoctorController(ModelMapper modelMapper, DoctorServiceImpl doctorService) {
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getAllDoctor() {
        return doctorService.getAllDoctor();
    }

    @PostMapping("doctor/new")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto) {
        DoctorDto newDoctor = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
    }

    @PutMapping("doctorupdate/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        DoctorDto newDoctor = doctorService.updateDoctor(id,doctorDto);
        return ResponseEntity.ok().body(newDoctor);
    }

    @DeleteMapping("doctordelete/{id}")
    public ResponseEntity<DoctorDto> deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<DoctorDto>(HttpStatus.OK);
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable(name = "id") Long id) {
        DoctorDto doctorResponse = doctorService.getDoctorById(id);
        return ResponseEntity.ok().body(doctorResponse);
    }

}

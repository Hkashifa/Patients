package com.example.patients.service;

import com.example.patients.dto.PatientDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PatientService {
    List<PatientDto> getAllPatient();

    public PatientDto createPatient(PatientDto patientDto);

    public PatientDto updatePatient(Long id, PatientDto patientdto);

    public PatientDto deletePatient(@PathVariable long id, @RequestBody PatientDto patientDto);

    PatientDto getPatientById(long id);
}

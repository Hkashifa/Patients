package com.example.patients.service.impl;

import com.example.patients.dto.DoctorDto;
import com.example.patients.dto.PatientDto;
import com.example.patients.entity.DoctorEntity;
import com.example.patients.entity.PatientEntity;
import com.example.patients.entity.enums.ActiveStatus;
import com.example.patients.repository.PatientRepository;
import com.example.patients.service.PatientService;
import com.example.patients.utils.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(ModelMapper modelMapper, PatientRepository patientRepository) {

        this.modelMapper = modelMapper;
        this.patientRepository = patientRepository;
    }
    @Override
    public List<PatientDto> getAllPatient() {
        //DTO to Entity
        List<PatientEntity> active_patients = new ArrayList<>();
        List<PatientEntity> all_patients = patientRepository.findAll();

        for (PatientEntity patient_holder : all_patients) {
            // Print all elements of ArrayList
            if (patient_holder.getActiveStatus() == ActiveStatus.ACTIVE) {

                active_patients.add(patient_holder);

            }
        }


        return active_patients.stream().map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        PatientEntity patientConverted = modelMapper.map(patientDto, PatientEntity.class);
        patientRepository.save(patientConverted);

        PatientDto patientResponse = modelMapper.map(patientConverted, PatientDto.class);
        return patientResponse;

    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientdto) {
        //Dto to entity
        PatientEntity patientEntityResponse = modelMapper.map(patientdto, PatientEntity.class);
        Optional<PatientEntity> updated_patient = patientRepository.findById(id);

        if (updated_patient.isPresent()) {
            PatientEntity patient_entity = updated_patient.get();

            patient_entity.setFirstName(patientEntityResponse.getFirstName());
            patient_entity.setLastName(patientEntityResponse.getLastName());
            patient_entity.setGender(patientEntityResponse.getGender());
            patient_entity.setEmail(patientEntityResponse.getEmail());
            patient_entity.setAge(patient_entity.getAge());
            patient_entity.setAddress(patient_entity.getAddress());
            patient_entity.setContactNumber(patient_entity.getContactNumber());


            patientRepository.save(patient_entity);//
            // entity to DTO
            PatientDto patientResponse = modelMapper.map(patient_entity, PatientDto.class);
            return patientResponse;
        }
        return null;
    }

    @Override
    public PatientDto deletePatient(long id, PatientDto patientDto) {
        PatientDto updated_patient = this.getPatientById(id);
        PatientEntity patient_entity = modelMapper.map(updated_patient, PatientEntity.class);

        patient_entity.setActiveStatus(ActiveStatus.INACTIVE);

        patientRepository.save(patient_entity);
        // entity to DTO
        PatientDto patientResponse = modelMapper.map(patient_entity, PatientDto.class);
        return patientResponse;

        //patientRepository.delete(patient);
    }

    @Override
    public PatientDto getPatientById(long id) {


        Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
        PatientEntity patient = optionalPatient
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return modelMapper.map(patient, PatientDto.class);
    }
}

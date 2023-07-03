package com.example.patients.service.impl;

import com.example.patients.dto.DoctorDto;
import com.example.patients.entity.DoctorEntity;
import com.example.patients.repository.DoctorRepository;
import com.example.patients.service.DoctorService;
import com.example.patients.utils.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final ModelMapper modelMapper;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(ModelMapper modelMapper, DoctorRepository doctorRepository) {
        this.modelMapper = modelMapper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDto> getAllDoctor() {
        List<DoctorEntity> all_doctors = doctorRepository.findAll();
        return all_doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        DoctorEntity doctorConverted = modelMapper.map(doctorDto, DoctorEntity.class);
        doctorRepository.save(doctorConverted);

        DoctorDto doctorResponse = modelMapper.map(doctorConverted, DoctorDto.class);
        return doctorResponse;
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        //Dto to entity
        DoctorEntity doctorEntityResponse = modelMapper.map(doctorDto, DoctorEntity.class);
        Optional<DoctorEntity> updated_doctor = doctorRepository.findById(id);

        if (updated_doctor.isPresent()) {
            DoctorEntity doctor_entity = updated_doctor.get();

            doctor_entity.setFirstName(doctorEntityResponse.getFirstName());
            doctor_entity.setLastName(doctorEntityResponse.getLastName());
            doctor_entity.setEmail(doctorEntityResponse.getEmail());
            doctor_entity.setPhoneNumber(doctorEntityResponse.getPhoneNumber());
            doctor_entity.setDepartment(doctorEntityResponse.getDepartment());
            doctor_entity.setSpecialisation(doctorEntityResponse.getSpecialisation());
            doctor_entity.setDegree(doctorEntityResponse.getDegree());

            doctorRepository.save(doctor_entity);//
            // entity to DTO
            DoctorDto doctorResponse = modelMapper.map(doctor_entity, DoctorDto.class);
            return doctorResponse;
        }
        return null;
    }

    @Override
    public void deleteDoctor(long id) {
        Optional<DoctorEntity> doctorOptional = doctorRepository.findById(id);
        DoctorEntity doctor = doctorOptional.orElseThrow(() ->
                new ResourceNotFoundException("Doctor", "id", id));

        doctorRepository.delete(doctor);
    }

    @Override
    public DoctorDto getDoctorById(long id) {

        Optional<DoctorEntity> optionalDoctor = doctorRepository.findById( id);
        DoctorEntity doctor = optionalDoctor.orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        return modelMapper.map(doctor, DoctorDto.class);
    }
}

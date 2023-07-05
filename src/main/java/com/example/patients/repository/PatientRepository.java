package com.example.patients.repository;

import com.example.patients.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository <PatientEntity,Long>{


//    @Query(value = "SELECT * FROM patient WHERE activeStatus = :ACTIVE", nativeQuery = true)
//    List<PatientEntity> findByActiveStatus(@Param("activeStatus") String activeStatus);
    List<PatientEntity> findByActiveStatus( int activeStatus);
}

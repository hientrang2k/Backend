package com.globits.Backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globits.Backend.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID>{

}

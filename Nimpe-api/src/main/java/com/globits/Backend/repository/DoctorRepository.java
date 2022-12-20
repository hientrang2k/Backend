package com.globits.Backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globits.Backend.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID>{

}

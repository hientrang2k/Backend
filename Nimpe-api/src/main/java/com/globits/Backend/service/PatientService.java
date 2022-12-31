package com.globits.Backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.Backend.domain.Patient;
import com.globits.Backend.dto.DoctorDto;
import com.globits.Backend.dto.ListDataDto;
import com.globits.Backend.dto.PatientDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.core.service.GenericService;

public interface PatientService extends GenericService<Patient, UUID>{
	ListDataDto searchByDto(SearchDto dto);
	public PatientDto saveOrUpdate(PatientDto dto, UUID id);
	public PatientDto createDoctor(PatientDto dto);
	public PatientDto updateDoctor(PatientDto dto);
	boolean deleteById(UUID id);
	public PatientDto getById(UUID id);
}

package com.globits.Backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.Backend.domain.Patient;
import com.globits.Backend.dto.PatientDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.core.service.GenericService;

public interface PatientService extends GenericService<Patient, UUID>{
	Page<PatientDto> searchByDto(SearchDto dto);
	public PatientDto saveOrUpdate(PatientDto dto, UUID id);
	boolean deleteById(UUID id);
	public PatientDto getById(UUID id);
}

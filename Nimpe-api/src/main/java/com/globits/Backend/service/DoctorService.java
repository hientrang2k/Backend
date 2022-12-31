package com.globits.Backend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.Backend.domain.Doctor;
import com.globits.Backend.dto.DoctorDto;
import com.globits.Backend.dto.ListDataDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.core.service.GenericService;

public interface DoctorService extends GenericService<Doctor, UUID>{
	ListDataDto searchByDto(SearchDto dto);
	public DoctorDto saveOrUpdate(DoctorDto dto, UUID id);
	public DoctorDto createDoctor(DoctorDto dto);
	public DoctorDto updateDoctor(DoctorDto dto);
	boolean deleteById(UUID id);
	public DoctorDto getById(UUID id);
}

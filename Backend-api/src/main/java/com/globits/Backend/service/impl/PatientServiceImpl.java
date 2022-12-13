package com.globits.Backend.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.Backend.domain.Patient;
import com.globits.Backend.dto.PatientDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.Backend.repository.PatientRepository;
import com.globits.Backend.service.PatientService;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.security.domain.User;
import com.globits.security.repository.UserRepository;

@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient, UUID> implements PatientService{

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	UserRepository repos1;
	
	@Override
	public Page<PatientDto> searchByDto(SearchDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDto saveOrUpdate(PatientDto dto, UUID id) {
		if (dto != null) {
			Patient entity = null;
			if (dto.getId() != null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity = patientRepository.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Patient();
			}
			
			entity.setGender(dto.getGender());
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setAddress(dto.getAddress());

			User user = null;
			if (dto.getUserDto() != null && dto.getUserDto().getId() != null) {
				user = repos1.getOne(dto.getUserDto().getId());
			}

			entity.setUser(user);

			entity = patientRepository.save(entity);
			if (entity != null) {
				return new PatientDto(entity);
			}
		}
		return null;
	}

	@Override
	public boolean deleteById(UUID id) {
		if (id != null) {
			Patient entity = patientRepository.getOne(id);
			if (entity != null) {
				patientRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public PatientDto getById(UUID id) {
		if (id != null) {
			Optional<Patient> entity = patientRepository.findById(id);
			if (entity.isPresent()) {
				return new PatientDto(entity.get());
			}
		}
		return null;
	}

}

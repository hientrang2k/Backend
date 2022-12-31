package com.globits.Backend.dto;

import java.util.UUID;

import com.globits.Backend.domain.Patient;
import com.globits.security.dto.UserDto;

public class PatientDto {
	private UUID id;
	private String address;
	private UserNewDto userDto;
	
	public PatientDto() {
		super();
	}
	public PatientDto(Patient entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.address = entity.getAddress();
			if(entity.getUser()!=null) {
				UserDto user = new UserDto(entity.getUser());
				UserNewDto userNewDto = new UserNewDto();
				userNewDto.setIdUser(user.getId());
				userNewDto.setDisplayName(user.getDisplayName());
				userNewDto.setEmail(user.getEmail());
				userNewDto.setGender(user.getPerson().getGender());
				userNewDto.setPhoneNumber(user.getPerson().getPhoneNumber());
				userNewDto.setUsername(user.getUsername());
				userNewDto.setRole(user.getRoles());
				this.userDto = userNewDto;
			}
		}
	}
	
	

	public UserNewDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserNewDto userDto) {
		this.userDto = userDto;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

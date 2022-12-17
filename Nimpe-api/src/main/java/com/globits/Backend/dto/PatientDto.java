package com.globits.Backend.dto;

import java.util.UUID;

import com.globits.Backend.domain.Patient;
import com.globits.security.dto.UserDto;

public class PatientDto {
	private UUID id;
	private String gender;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private UserDto userDto;
	
	public PatientDto() {
		super();
	}
	public PatientDto(Patient entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.gender = entity.getGender();
			this.name = entity.getName();
			this.email = entity.getEmail();
			this.phoneNumber = entity.getPhoneNumber();
			this.address = entity.getAddress();
			if(entity.getUser()!=null) {
				this.userDto = new UserDto(entity.getUser());
			}
		}
	}
	
	
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

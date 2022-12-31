package com.globits.Backend.dto;

import java.util.Set;

import com.globits.security.dto.RoleDto;

public class UserNewDto {
	private Long idUser;
	private String email;
	private String displayName;
	private String gender;
	private String phoneNumber;
	private String username;
	private String password;
	private Set<RoleDto> role;
	public Set<RoleDto> getRole() {
		return role;
	}
	public void setRole(Set<RoleDto> role) {
		this.role = role;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

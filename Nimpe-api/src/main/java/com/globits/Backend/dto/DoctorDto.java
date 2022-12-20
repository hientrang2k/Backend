package com.globits.Backend.dto;

import java.util.UUID;

import com.globits.Backend.domain.Doctor;
import com.globits.core.dto.BaseObjectDto;
import com.globits.security.dto.UserDto;

public class DoctorDto {
	private UUID id;
	private UserDto userDto;
	private String subDescription;
	private String description;
	private String price;
	private String imgUrl;
	
	public DoctorDto() {
		super();
	}
	public DoctorDto(Doctor entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.subDescription = entity.getSubDescription();
			this.description = entity.getDescription();
			this.price = entity.getPrice();
			this.imgUrl = entity.getImgUrl();
			if(entity.getUser()!=null) {
				this.userDto = new UserDto(entity.getUser());
			}
		}
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getSubDescription() {
		return subDescription;
	}
	public void setSubDescription(String subDescription) {
		this.subDescription = subDescription;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}

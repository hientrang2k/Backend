package com.globits.Backend.dto;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.globits.Backend.domain.Doctor;
import com.globits.core.dto.BaseObjectDto;
import com.globits.security.dto.UserDto;

public class DoctorDto extends BaseObjectDto{
	private UserDto userDto;
	private String description;
	private String price;
	private String imgUrl;
	
	public DoctorDto() {
		super();
	}
	public DoctorDto(Doctor entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.description = entity.getDescription();
			this.price = entity.getPrice();
			this.imgUrl = entity.getImgUrl();
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
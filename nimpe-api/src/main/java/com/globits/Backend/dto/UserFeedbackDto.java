package com.globits.Backend.dto;

import com.globits.Backend.domain.UserFeedback;
import com.globits.core.dto.BaseObjectDto;
import com.globits.security.dto.UserDto;

public class UserFeedbackDto extends BaseObjectDto {

	private UserDto user;
	private String feedback;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public UserFeedbackDto() {
		super();
	}
	
	public UserFeedbackDto(UserFeedback entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.createDate = entity.getCreateDate();
			this.feedback = entity.getFeedback();
			this.user =  new UserDto(entity.getUser());
		}
	}
	
}

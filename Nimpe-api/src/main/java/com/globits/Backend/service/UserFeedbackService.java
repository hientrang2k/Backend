//package com.globits.Backend.service;
//
//import java.util.UUID;
//
//import org.springframework.data.domain.Page;
//
//import com.globits.core.service.GenericService;
//import com.globits.Backend.domain.UserFeedback;
//import com.globits.Backend.dto.UserFeedbackDto;
//import com.globits.Backend.functiondto.UserFeedbackSearchDto;
//
//public interface UserFeedbackService extends GenericService<UserFeedback, UUID> {
//	
//	Page<UserFeedbackDto> searchByDto(UserFeedbackSearchDto dto);
//	public UserFeedbackDto saveOrUpdate(UserFeedbackDto dto, UUID id);
//	boolean deleteById(UUID id);
//	public UserFeedbackDto getById(UUID id);
//}

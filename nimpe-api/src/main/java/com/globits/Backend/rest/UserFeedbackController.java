//package com.globits.Backend.rest;
//
//import java.util.UUID;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.globits.Backend.dto.UserFeedbackDto;
//import com.globits.Backend.functiondto.UserFeedbackSearchDto;
//import com.globits.Backend.service.UserFeedbackService;
//
//@RestController
//@RequestMapping("/api/user-feedback")
//public class UserFeedbackController {
//	@Autowired
//	UserFeedbackService feedbackService;
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<UserFeedbackDto> save(@Valid @RequestBody UserFeedbackDto dto) {
//		UserFeedbackDto result = feedbackService.saveOrUpdate(dto,null);
//		return new ResponseEntity<UserFeedbackDto>(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
//	public ResponseEntity<UserFeedbackDto> update(@RequestBody UserFeedbackDto dto, @PathVariable("id") UUID id) {
//		UserFeedbackDto result = feedbackService.saveOrUpdate(dto,id);
//		return new ResponseEntity<UserFeedbackDto>(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
//	public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
//		Boolean result = feedbackService.deleteById(id);
//		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/{id}",method = RequestMethod.GET)
//	public ResponseEntity<UserFeedbackDto> getById(@PathVariable("id") UUID id) {
//		UserFeedbackDto result = feedbackService.getById(id);
//		return new ResponseEntity<UserFeedbackDto>(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
//	public ResponseEntity<Page<UserFeedbackDto>> searchByPage(@RequestBody UserFeedbackSearchDto searchDto) {
//		Page<UserFeedbackDto> page = this.feedbackService.searchByDto(searchDto);
//		return new ResponseEntity<Page<UserFeedbackDto>>(page, HttpStatus.OK);
//	}
//}

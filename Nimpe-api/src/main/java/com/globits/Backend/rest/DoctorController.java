package com.globits.Backend.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.Backend.dto.DoctorDto;
import com.globits.Backend.dto.ListDoctorDto;
import com.globits.Backend.dto.UserFeedbackDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.Backend.functiondto.UserFeedbackSearchDto;
import com.globits.Backend.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	@Autowired
    private DoctorService Service;
	
	@PostMapping(value = "/create")
    public ResponseEntity<DoctorDto> save(@Valid @RequestBody DoctorDto dto) {
		DoctorDto result = Service.createDoctor(dto);
        return new ResponseEntity<DoctorDto>(result, HttpStatus.OK);
    }

	
	@PutMapping(value = "/update")
    public ResponseEntity<DoctorDto> update(@Valid @RequestBody DoctorDto dto) {
    	DoctorDto result = Service.updateDoctor(dto);
        return new ResponseEntity<DoctorDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete_by_id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        Boolean result = Service.deleteById(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_by_id/{id}", method = RequestMethod.GET)
    public ResponseEntity<DoctorDto> getById(@PathVariable("id") UUID id) {
    	DoctorDto result = Service.getById(id);
        return new ResponseEntity<DoctorDto>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ListDoctorDto> searchByPage(@RequestBody SearchDto searchDto) {
    	ListDoctorDto page = Service.searchByDto(searchDto);
		return new ResponseEntity<ListDoctorDto>(page, HttpStatus.OK);
	}
}

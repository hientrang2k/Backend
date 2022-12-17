package com.globits.Backend.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.globits.Backend.dto.PatientDto;
import com.globits.Backend.dto.UserFeedbackDto;
import com.globits.Backend.service.PatientService;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PatientDto> save(@Valid @RequestBody PatientDto dto) {
        PatientDto result = patientService.saveOrUpdate(dto, null);
        return new ResponseEntity<PatientDto>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<PatientDto> update(@Valid @RequestBody PatientDto dto) {
        PatientDto result = patientService.saveOrUpdate(dto, dto.getId());
        return new ResponseEntity<PatientDto>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        Boolean result = patientService.deleteById(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PatientDto> getById(@PathVariable("id") UUID id) {
        PatientDto result = patientService.getById(id);
        return new ResponseEntity<PatientDto>(result, HttpStatus.OK);
    }
}

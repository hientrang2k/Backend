package com.globits.Backend.rest;

import java.util.List;

import com.globits.core.dto.PersonDto;
import com.globits.Backend.dto.UserrDto;
import com.globits.Backend.service.UserInfoService;
import com.globits.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.globits.Backend.functiondto.SearchDto;
import com.globits.security.dto.UserDto;

@RestController
@RequestMapping("/api/user-search")
public class RestUsersController {

    @Autowired
    UserService userService;
    @Autowired
    UserInfoService service;

    @PostMapping("/users")
    public UserDto saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        List<UserDto> result = service.getAllInfoByRoleUser();
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserrDto dto, @PathVariable Long id) {
        PersonDto result = service.update(dto, id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        UserDto result = userService.deleteById(id);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/searchByDto")
    public ResponseEntity<?> searchByDto(@RequestBody SearchDto dto) {
        Page<UserDto> result = service.searchByPage(dto);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    
}

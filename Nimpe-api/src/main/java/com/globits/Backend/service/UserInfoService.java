package com.globits.Backend.service;

import com.globits.core.dto.PersonDto;
import com.globits.Backend.dto.UserInfoDto;
import com.globits.Backend.dto.UserrDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface UserInfoService {
    User getCurrentUser();
    UserDto getCurrentUserDto();
    UserInfoDto getAllInfoByUserLogin();
    List<RoleDto> getRoleUser();
    List<UserDto> getAllInfoByRoleUser();
    PersonDto update(UserrDto dto, Long id);
    Page<UserDto> searchByPage(SearchDto dto);
}

package com.globits.Backend.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.security.domain.User;
import com.globits.security.dto.UserDto;

public class UserInfoDto extends BaseObjectDto {

    private UserDto userDto;
    private boolean isAdmin = false;
    private boolean isUser = false;

    public UserInfoDto() {
    }

    public UserInfoDto(User user) {
        userDto = new UserDto(user);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}

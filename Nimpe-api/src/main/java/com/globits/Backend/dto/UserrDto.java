package com.globits.Backend.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.OrganizationDto;
import com.globits.core.dto.PersonDto;
import com.globits.security.domain.Role;
import com.globits.security.domain.User;
import com.globits.security.domain.UserGroup;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserGroupDto;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserrDto extends AuditableEntityDto {
    private Long id;
    private String phone;
    private String displayName;
    private String username;
    private String password;
    private String oldPassword;
    private String confirmPassword;
    private boolean isSetPassword;
    private boolean changePass;
    private Boolean active;
    private String lastName;
    private String firstName;
    private Date dob;
    private String birthPlace;
    private String email;
    private PersonDto person;
    private boolean hasPhoto;
    private Set<RoleDto> roles = new HashSet();
    private Set<UserGroupDto> groups = new HashSet();
    private OrganizationDto org;

    public OrganizationDto getOrg() {
        return this.org;
    }

    public void setOrg(OrganizationDto org) {
        this.org = org;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UserrDto() {
    }

    public UserrDto(User entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.displayName = "";
            this.username = entity.getUsername();
            this.password = entity.getPassword();
            this.confirmPassword = entity.getConfirmPassword();
            this.active = entity.getActive();
            this.email = entity.getEmail();
            if (entity.getPerson() != null) {
                this.person = new PersonDto(entity.getPerson());
                if (entity.getPerson().getDisplayName() != null) {
                    this.displayName = entity.getPerson().getDisplayName();
                } else {
                    this.displayName = this.person.getFirstName() + " " + this.person.getLastName();
                }

                this.dob = this.person.getBirthDate();
                this.birthPlace = this.person.getBirthPlace();
                this.hasPhoto = entity.getPerson().getImagePath() != null && entity.getPerson().getImagePath().length() > 0;
            }

            Iterator var3;
            if (entity.getRoles() != null) {
                this.roles.clear();
                var3 = entity.getRoles().iterator();

                while(var3.hasNext()) {
                    Role role = (Role)var3.next();
                    this.roles.add(new RoleDto(role));
                }
            }

            if (entity.getGroups() != null) {
                this.groups.clear();
                var3 = entity.getGroups().iterator();

                while(var3.hasNext()) {
                    UserGroup group = (UserGroup)var3.next();
                    this.groups.add(new UserGroupDto(group));
                }
            }

            if (entity.getOrg() != null) {
                this.org = new OrganizationDto();
                this.org.setCode(entity.getOrg().getCode());
                this.org.setName(entity.getOrg().getName());
                this.org.setId(entity.getOrg().getId());
            }

            this.setCreateDate(entity.getCreateDate());
            this.setCreatedBy(entity.getCreatedBy());
            this.setModifyDate(entity.getModifyDate());
            this.setModifiedBy(entity.getModifiedBy());
        }
    }

    public User toEntity() {
        User entity = new User();
        if (this.id != null) {
            entity.setId(this.id);
        }

        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setActive(this.active);
        entity.setEmail(this.email);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        if (this.person != null) {
            entity.setPerson(this.person.toEntity());
        }

        Iterator var3;
        if (this.roles.size() > 0) {
            var3 = this.roles.iterator();

            while(var3.hasNext()) {
                RoleDto dto = (RoleDto)var3.next();
                entity.getRoles().add(dto.toEntity());
            }
        }

        if (this.groups.size() > 0) {
            var3 = this.groups.iterator();

            while(var3.hasNext()) {
                UserGroupDto dto = (UserGroupDto)var3.next();
                entity.getGroups().add(dto.toEntity());
            }
        }

        return entity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isChangePass() {
        return changePass;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isSetPassword() {
        return this.isSetPassword;
    }

    public void setSetPassword(boolean isSetPassword) {
        this.isSetPassword = isSetPassword;
    }

    public boolean getChangePass() {
        return this.changePass;
    }

    public void setChangePass(boolean changePass) {
        this.changePass = changePass;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonDto getPerson() {
        return this.person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public boolean isHasPhoto() {
        return this.hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public Set<RoleDto> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<UserGroupDto> getGroups() {
        return this.groups;
    }

    public void setGroups(Set<UserGroupDto> groups) {
        this.groups = groups;
    }

    public String getImagePath() {
        return this.person != null ? this.person.getImagePath() : null;
    }
}

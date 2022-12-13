package com.globits.Backend.service.impl;

import com.globits.core.domain.Person;
import com.globits.core.dto.PersonDto;
import com.globits.core.repository.PersonRepository;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.Backend.ServerConst;
import com.globits.Backend.dto.UserInfoDto;
import com.globits.Backend.dto.UserrDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.Backend.service.UserInfoService;
import com.globits.security.domain.Role;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.dto.UserGroupDto;
import com.globits.security.repository.RoleRepository;
import com.globits.security.repository.UserRepository;
import com.globits.security.service.UserService;
import com.google.firebase.auth.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class UserInfoServiceImpl extends GenericServiceImpl<User, Long> implements UserInfoService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName;
        User user = null;
        if(authentication != null && authentication.getPrincipal() != null) {
            if(authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
                user = userRepository.findByUsername(userName);
            } else {
                user = (User) authentication.getPrincipal();
                if(user != null) {
                    userName = user.getUsername();
                }
            }
        }
        if (user != null && user.getUsername() != null) {
            User entity = userRepository.findByUsernameAndPerson(user.getUsername());

            if (entity != null)
                return entity;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUserDto() {
        User entity = getCurrentUser();
        if (entity != null)
            return new UserDto(entity);

        return null;
    }

    @Override
    public UserInfoDto getAllInfoByUserLogin() {
        UserInfoDto userInfoDto = null;
        UserDto user = getCurrentUserDto();
        if(user != null && user.getId() != null) {
            userInfoDto = new UserInfoDto();
            userInfoDto.setUserDto(user);
            userInfoDto = getRolesByUser(userInfoDto, user);
        }
        return userInfoDto;
    }



    private UserInfoDto getRolesByUser(UserInfoDto info, UserDto currentUser) {
        if (currentUser != null && info != null) {
            for (RoleDto role : currentUser.getRoles()) {
                if (role.getAuthority().equals(ServerConst.ROLE_ADMIN)
                        || role.getName().equals(ServerConst.ROLE_ADMIN)) {
                    info.setAdmin(true);
                }
                if (role.getAuthority().equals(ServerConst.ROLE_USER)
                        || role.getName().equals(ServerConst.ROLE_USER)) {
                    info.setUser(true);
                }

            }
            return info;
        }
        return null;
    }

    @Override
    public List<RoleDto> getRoleUser() {
        List<RoleDto> roleDto = new ArrayList<>();

        UserDto user = getCurrentUserDto();
        if(user.getRoles() != null && user.getRoles().size() >0) {
            for (RoleDto role : user.getRoles()) {
                roleDto.add(role);
            }
        }
        return roleDto;
    }

    @Override
    public List<UserDto> getAllInfoByRoleUser() {

        String sql = "select new com.globits.security.dto.UserDto(entity) from User as entity join entity.roles r where r.id=2 ";
        Query q = manager.createQuery(sql, UserDto.class);
        List<UserDto> list = q.getResultList();
        return list;

    }

    @Override
    public PersonDto update(UserrDto dto , Long id) {

        if(dto != null) {
            User entity = null;
            Person person = null;
            if(id != null) {
                entity = userRepository.getOne(id);
            } else {
                return null;
            }

            person = new Person();
            person.setPhoneNumber(dto.getPhone());
            person.setUser(entity);
            person.setFirstName(dto.getFirstName());
            person.setLastName(dto.getLastName());
            person.setDisplayName(dto.getDisplayName());
            person.setEmail(dto.getEmail());

            person = personRepository.save(person);
            if(person != null){
                return new PersonDto(person);
            }
        }

        return null;
    }

    @Override
    public Page<UserDto> searchByPage(SearchDto dto) {
        if (dto == null)
            return null;

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0)
            pageIndex--;
        else
            pageIndex = 0;

        String whereClause = "";

        String orderBy = " ORDER BY entity.createDate DESC";


        String sqlCount = "select count(entity.id) from User as entity join entity.roles r where (1=1)";
        String sql = "select new com.globits.security.dto.UserDto(entity) from User as entity join entity.roles r  where (1=1)";

        if (dto.getSearchText() != null && StringUtils.hasText(dto.getSearchText()))
            whereClause += " AND (UPPER(entity.email) LIKE UPPER(:text) OR UPPER(entity.username) LIKE UPPER(:text) OR UPPER(entity.person.displayName) LIKE UPPER(:text))";

        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, UserDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getSearchText() != null && StringUtils.hasText(dto.getSearchText())) {
            q.setParameter("text", '%' + dto.getSearchText() + '%');
            qCount.setParameter("text", '%' + dto.getSearchText() + '%');
        }

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<UserDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<UserDto> result = new PageImpl<>(entities, pageable, count);
        return result;
    }
}

package com.globits.Backend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.globits.Backend.domain.Doctor;
import com.globits.Backend.dto.DoctorDto;
import com.globits.Backend.dto.ListDataDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.Backend.repository.DoctorRepository;
import com.globits.Backend.service.DoctorService;
import com.globits.core.dto.PersonDto;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.repository.UserRepository;
import com.globits.security.service.UserService;

@Service
public class DoctorServiceImpl extends GenericServiceImpl<Doctor, UUID> implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	UserRepository repos1;
	@Autowired
	UserService userService;
	
	@Override
	public DoctorDto saveOrUpdate(DoctorDto dto, UUID id) {
		if (dto != null) {
			Doctor entity = null;
			if (dto.getId() != null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity = doctorRepository.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Doctor();
			}
			toEntity(entity, dto);
			User user = null;
			if (dto.getUserDto() != null && dto.getUserDto().getIdUser() != null) {
				user = repos1.getOne(dto.getUserDto().getIdUser());
			}
			entity.setUser(user);
			entity = doctorRepository.save(entity);
			if (entity != null) {
				return new DoctorDto(entity);
			}
		}
		return null;
	}

	private void toEntity(Doctor entity,DoctorDto dto) {
		entity.setSubDescription(dto.getSubDescription());
		entity.setDescription(dto.getSubDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPosition(dto.getPosition());
	}
	
	@Override
	public boolean deleteById(UUID id) {
		if (id != null) {
			DoctorDto entity = this.getById(id);
			if(!ObjectUtils.isEmpty(entity.getUserDto())) {
				userService.deleteById(entity.getUserDto().getIdUser());
			}
			if (entity != null) {
				doctorRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public DoctorDto getById(UUID id) {
		if (id != null) {
			Optional<Doctor> entity = doctorRepository.findById(id);
			if (entity.isPresent()) {
				return new DoctorDto(entity.get());
			}
		}
		return null;
	}

	@Override
	public DoctorDto createDoctor(DoctorDto dto) {
		//th??m m????i m????t ta??i khoa??n  
		UserDto userCreate = createUpdateUser(dto);
		// th??m m????i th??ng tin ba??c si??
		dto.getUserDto().setIdUser(userCreate.getId());
		DoctorDto doctorDto = this.saveOrUpdate(dto, null);
		return doctorDto;
	}
	
	@Override
	public DoctorDto updateDoctor(DoctorDto dto) {
		//th??m m????i m????t ta??i khoa??n  
		UserDto userCreate = createUpdateUser(dto);
		// th??m m????i th??ng tin ba??c si??
		dto.getUserDto().setIdUser(userCreate.getId());
		DoctorDto doctorDto = this.saveOrUpdate(dto, dto.getId());
		return doctorDto;
	}

	private UserDto createUpdateUser(DoctorDto dto) {
		UserDto user = new UserDto();
		if(!StringUtils.isEmpty(dto.getUserDto().getIdUser())) {
			user.setId(dto.getUserDto().getIdUser());
		}
		Set<RoleDto> role = new HashSet<>();
		RoleDto roleDto = new RoleDto();
		roleDto.setId(3L);
		roleDto.setDescription("DOCTOR");
		role.add(roleDto);
		user.setRoles(role);
		user.setActive(true);
		user.setUsername(dto.getUserDto().getUsername());
		user.setPassword(dto.getUserDto().getPassword());
		PersonDto personDto = new PersonDto();
		personDto.setPhoneNumber(dto.getUserDto().getPhoneNumber());
		personDto.setGender(dto.getUserDto().getGender());
		personDto.setDisplayName(dto.getUserDto().getDisplayName());
		user.setPerson(personDto);
		user.setDisplayName(dto.getUserDto().getDisplayName());
		user.setEmail(dto.getUserDto().getEmail());
		user.setConfirmPassword(dto.getUserDto().getPassword());
		UserDto userCreate = userService.save(user);
		return userCreate;
	}
	
	
	@Override
	public ListDataDto searchByDto(SearchDto dto) {
		if (dto == null) {
            return null;
        }
        int pageIndex = dto.getPageIndex() > 0  ? dto.getPageIndex() : 1;
        int pageSize = dto.getPageSize() > 0  ? dto.getPageSize() : 10;

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }

        String whereClause = "";
        String orderBy = " ";
        String sqlCount ="select count(*) from  Doctor as e where (1=1)  ";
        String sql = "select new com.globits.Backend.dto.DoctorDto(e) from Doctor as e where (1=1) ";

        if (StringUtils.hasText(dto.getText()))
            whereClause += "AND e.user.username LIKE :text "
            			+ "	or e.user.email LIKE :text ";

        sql += whereClause + orderBy;
        sqlCount += whereClause;
        Query q = manager.createQuery(sql, DoctorDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (StringUtils.hasText(dto.getText())) {
            q.setParameter("text", '%' + dto.getText().trim() + '%');
        	qCount.setParameter("text", '%' + dto.getText().trim() + '%');
        }

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<Object> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();
        ListDataDto listDataDto = new ListDataDto();
        listDataDto.setData(entities);
        listDataDto.setPage(pageIndex + 1);
        listDataDto.setSize(pageSize);
        listDataDto.setTotal(count);
        return listDataDto;
	}
}

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

import com.globits.Backend.domain.Patient;
import com.globits.Backend.dto.ListDataDto;
import com.globits.Backend.dto.PatientDto;
import com.globits.Backend.functiondto.SearchDto;
import com.globits.Backend.repository.PatientRepository;
import com.globits.Backend.service.PatientService;
import com.globits.core.dto.PersonDto;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.repository.UserRepository;
import com.globits.security.service.UserService;

@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient, UUID> implements PatientService{

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	UserRepository repos1;
	@Autowired
	UserService userService;
	
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
        String sqlCount ="select count(*) from  Patient as e where (1=1)  ";
        String sql = "select new com.globits.Backend.dto.PatientDto(e) from Patient as e where (1=1) ";

        if (StringUtils.hasText(dto.getText()))
            whereClause += "AND e.user.username LIKE :text "
            			+ "	or e.user.email LIKE :text ";

        sql += whereClause + orderBy;
        sqlCount += whereClause;
        Query q = manager.createQuery(sql, PatientDto.class);
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

	@Override
	public PatientDto saveOrUpdate(PatientDto dto, UUID id) {
		if (dto != null) {
			Patient entity = null;
			if (dto.getId() != null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity = patientRepository.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Patient();
			}
			toEntity(entity,dto);
			User user = null;
			if (dto.getUserDto() != null && dto.getUserDto().getIdUser() != null) {
				user = repos1.getOne(dto.getUserDto().getIdUser());
			}
			entity.setUser(user);

			entity = patientRepository.save(entity);
			if (entity != null) {
				return new PatientDto(entity);
			}
		}
		return null;
	}
	
	private void toEntity(Patient entity,PatientDto dto) {
		entity.setAddress(dto.getAddress());
	}
	
	@Override
	public boolean deleteById(UUID id) {
		if (id != null) {
			PatientDto entity = this.getById(id);
			if(!ObjectUtils.isEmpty(entity.getUserDto())) {
				userService.deleteById(entity.getUserDto().getIdUser());
			}
			if (entity != null) {
				patientRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public PatientDto getById(UUID id) {
		if (id != null) {
			Optional<Patient> entity = patientRepository.findById(id);
			if (entity.isPresent()) {
				return new PatientDto(entity.get());
			}
		}
		return null;
	}
	
	@Override
	public PatientDto createDoctor(PatientDto dto) {
		//thêm mới một tài khoản  
		UserDto userCreate = createUpdateUser(dto);
		// thêm mới thông tin bác sĩ
		dto.getUserDto().setIdUser(userCreate.getId());
		PatientDto patientDto = this.saveOrUpdate(dto, null);
		return patientDto;
	}
	
	@Override
	public PatientDto updateDoctor(PatientDto dto) {
		//thêm mới một tài khoản  
		UserDto userCreate = createUpdateUser(dto);
		// thêm mới thông tin bác sĩ
		dto.getUserDto().setIdUser(userCreate.getId());
		PatientDto patientDto = this.saveOrUpdate(dto, dto.getId());
		return patientDto;
	}
	
	private UserDto createUpdateUser(PatientDto dto) {
		UserDto user = new UserDto();
		if(!StringUtils.isEmpty(dto.getUserDto().getIdUser())) {
			user.setId(dto.getUserDto().getIdUser());
		}
		Set<RoleDto> role = new HashSet<>();
		RoleDto roleDto = new RoleDto();
		roleDto.setId(2L);
		roleDto.setDescription("User");
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


}

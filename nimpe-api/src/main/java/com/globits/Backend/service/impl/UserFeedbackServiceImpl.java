//package com.globits.Backend.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import javax.persistence.Query;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.globits.core.service.impl.GenericServiceImpl;
//import com.globits.Backend.dto.UserFeedbackDto;
//import com.globits.Backend.entity.UserFeedback;
//import com.globits.Backend.functiondto.UserFeedbackSearchDto;
//import com.globits.Backend.repository.UserFeedbackRepository;
//import com.globits.Backend.service.UserFeedbackService;
//import com.globits.security.domain.User;
//import com.globits.security.repository.UserRepository;
//
//@Service
//public class UserFeedbackServiceImpl extends GenericServiceImpl<UserFeedback, UUID> implements UserFeedbackService {
//
//	@Autowired
//	UserFeedbackRepository repos;
//
//	@Autowired
//	UserRepository repos1;
//
//	@Override
//	public UserFeedbackDto saveOrUpdate(UserFeedbackDto dto, UUID id) {
//		if (dto != null) {
//			UserFeedback entity = null;
//			if (dto.getId() != null) {
//				if (dto.getId() != null && !dto.getId().equals(id)) {
//					return null;
//				}
//				entity = repos.getOne(dto.getId());
//			}
//			if (entity == null) {
//				entity = new UserFeedback();
//			}
//
//			entity.setFeedback(dto.getFeedback());
//
//			User user = null;
//			if (dto.getUser() != null && dto.getUser().getId() != null) {
//				user = repos1.getOne(dto.getUser().getId());
//			}
//
//			entity.setUser(user);
//
//			entity = repos.save(entity);
//			if (entity != null) {
//				return new UserFeedbackDto(entity);
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public boolean deleteById(UUID id) {
//		if (id != null) {
//			UserFeedback entity = repos.getOne(id);
//			if (entity != null) {
//				repos.deleteById(id);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public UserFeedbackDto getById(UUID id) {
//		if (id != null) {
//			Optional<UserFeedback> entity = repos.findById(id);
//			if (entity.isPresent()) {
//				return new UserFeedbackDto(entity.get());
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Page<UserFeedbackDto> searchByDto(UserFeedbackSearchDto dto) {
//		if (dto == null) {
//            return null;
//        }
//        int pageIndex = dto.getPageIndex() > 0  ? dto.getPageIndex() : 1;
//        int pageSize = dto.getPageSize() > 0  ? dto.getPageSize() : 10;
//
//        if (pageIndex > 0) {
//            pageIndex--;
//        } else {
//            pageIndex = 0;
//        }
//
//        String whereClause = "";
//        String orderBy = " ";
//        String sqlCount ="select count(*) from  UserFeedback as e where (1=1)  ";
//        String sql = "select new com.globits.nimpe.dto.UserFeedbackDto(e) from UserFeedback as e where (1=1) ";
//
//        if (StringUtils.hasText(dto.getText()))
//            whereClause += "AND e.user.username LIKE :text "
//            			+ "	or e.user.email LIKE :text "
//            			+ "	or e.feedback LIKE :text "
//            			+ "	or e.createDate LIKE :text";
//
//        sql += whereClause + orderBy;
//        sqlCount += whereClause;
//        Query q = manager.createQuery(sql, UserFeedbackDto.class);
//        Query qCount = manager.createQuery(sqlCount);
//
//        if (StringUtils.hasText(dto.getText())) {
//            q.setParameter("text", '%' + dto.getText().trim() + '%');
//        	qCount.setParameter("text", '%' + dto.getText().trim() + '%');
//        }
//
//        int startPosition = pageIndex * pageSize;
//        q.setFirstResult(startPosition);
//        q.setMaxResults(pageSize);
//        List<UserFeedbackDto> entities = q.getResultList();
//        long count = (long) qCount.getSingleResult();
//        Pageable pageable = PageRequest.of(pageIndex, pageSize);
//
//        return new PageImpl<>(entities, pageable, count);
//	}
//
//}

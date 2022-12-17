package com.globits.Backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globits.Backend.domain.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, UUID>{

}

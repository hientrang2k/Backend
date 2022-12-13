package com.globits.Backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;
import com.globits.security.domain.User;

@Entity
@Table(name = "tbl_feedback")
@XmlRootElement
public class UserFeedback extends BaseObject{
private static final long serialVersionUID = 1L;
	
	@Column(name = "feedback")
	private String feedback;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}

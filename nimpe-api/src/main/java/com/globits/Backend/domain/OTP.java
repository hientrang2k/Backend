package com.globits.Backend.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@XmlRootElement
@Table(name = "tbl_OPT")
@Entity
public class OTP extends BaseObject {
    private static final long serialVersionUID = 1L;

    @Column(name = "userID")
    private Long userID;

    @Column(name = "token")
    private String token;

    @Column(name = "expired")
    private LocalDateTime expired;

    @Column(name = "email")
    private String email;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

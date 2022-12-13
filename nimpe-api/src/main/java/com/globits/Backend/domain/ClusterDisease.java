package com.globits.Backend.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_cluster_disease")
@XmlRootElement
public class ClusterDisease extends BaseObject{
	
    private static final long serialVersionUID = 1L;
    @Column(name = "investigation_date")
    private Date investigationDate;
    
	@Column(name = "longitude")
	private String longitude;// Kinh độ

	@Column(name = "latitude")
	private String latitude;// Vĩ độ
	
	@Column(name = "type_survey")
	private Integer typeSurvey;// Loại phiếu điều tra

	public Date getInvestigationDate() {
		return investigationDate;
	}

	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getTypeSurvey() {
		return typeSurvey;
	}

	public void setTypeSurvey(Integer typeSurvey) {
		this.typeSurvey = typeSurvey;
	}

	public void setTypeSurvey(int typeSurvey) {
		this.typeSurvey = typeSurvey;
	}
}

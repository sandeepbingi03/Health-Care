package com.interview.springboot.rest.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "dependent")
public class Dependent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5175749933900300765L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "dependentName", nullable = true)
	private String name;
	
	@Column(name = "birthDate", nullable = true)
	private Date birthDate;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "fk_enroll_user") private EnrollUser enroll_user;
	 */
	public Dependent() {}
	
	public Dependent(String name, Date birthDate, EnrollUser enroll) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		//this.enroll_user = enroll;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return " { id : " + id + ", name : " + name + ", birthDate :" + birthDate + "}";
	}

	/*
	 * public EnrollUser getEnroll() { return enroll; }
	 * 
	 * public void setEnroll(EnrollUser enroll) { this.enroll = enroll; }
	 */


}

package com.interview.springboot.rest.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*
 * 
 * 
{
 "name" : "TEST",
	"activationStatus": true,
	"birthDate": "1991-01-01",
	"phoneNumber": "9876543210",
    "list" : [
    	{ "name" : "DEPENDENT",
		  "birthDate": "1995-01-01"
    	}
	]
}
 */
@Entity
@Table(name = "enrollment")
@Access(value=AccessType.FIELD)
public class EnrollUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1908417777875109791L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private boolean activationStatus;
	private Date birthDate;
	private String phoneNumber;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Dependent> list;
	
	public EnrollUser() {}
	
	public EnrollUser(String name,boolean activationStatus,Date birthDate,String phoneNumber, List<Dependent> list) {
		this.activationStatus = activationStatus;
		this.name = name;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "fullName", nullable = false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status")
	public boolean isActivationStatus() {
		return activationStatus;
	}


	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	
	@Column(name = "phone", nullable = true)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Column(name = "birthDay", nullable = false)
	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Dependent> getList() {
		return list;
	}


	public void setList(List<Dependent> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return " { id : " + id + ", name :" + name + ", activationStatus :" + activationStatus + ", birthDate :"
				+ birthDate + ", phoneNumber :" + phoneNumber + ", list : [" + list + "]}";
	}


	
}

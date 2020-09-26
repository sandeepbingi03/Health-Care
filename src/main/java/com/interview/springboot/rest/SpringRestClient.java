package com.interview.springboot.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.interview.springboot.rest.model.Dependent;
import com.interview.springboot.rest.model.EnrollUser;

public class SpringRestClient {

	private static final String GET_ALL_ENROLLMENT_ENDPOINT_URL = "http://localhost:8080/api/v1/enroll";
	private static final String GET_ENROLLMENT_ENDPOINT_URL = "http://localhost:8080/api/v1/enroll/{id}";
	private static final String CREATE_ENROLLMENT_ENDPOINT_URL = "http://localhost:8080/api/v1/enroll";
	private static final String UPDATE_ENROLLMENT_ENDPOINT_URL = "http://localhost:8080/api/v1/enroll/{id}";
	private static final String DELETE_ENROLLMENT_ENDPOINT_URL = "http://localhost:8080/api/v1/enroll/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();

		// Step1: first create a new enrollee
		springRestClient.createEnrollemnt();

		// Step 2: get new created enrollee from step1
		springRestClient.getEnrollmentById();

		// Step3: get all enrollee
		springRestClient.getAllEnrollments();

		// Step4: Update enrollee with id = 1
		springRestClient.updateEnrollment();

		// Step5: Delete enrollee with id = 1
		springRestClient.deleteEnrollment();
	}

	private void getAllEnrollments() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_ENROLLMENT_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getEnrollmentById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		EnrollUser result = restTemplate.getForObject(GET_ENROLLMENT_ENDPOINT_URL, EnrollUser.class, params);

		System.out.println(result);
	}

	private void createEnrollemnt() {

		EnrollUser enrollUser = new EnrollUser();

		enrollUser.setActivationStatus(true);
		enrollUser.setId(10);
		enrollUser.setName("Sandeep2");
		enrollUser.setPhoneNumber("9876543210");
		enrollUser.setBirthDate(Date.valueOf("1991-01-01"));

		List<Dependent> dependents = new ArrayList<Dependent>();
		Dependent dependent = new Dependent();
		dependent.setBirthDate(Date.valueOf("2005-01-01"));
		dependent.setName("Dependent1");
		dependents.add(dependent);
		
		enrollUser.setList(dependents);

		RestTemplate restTemplate = new RestTemplate();
		EnrollUser result = restTemplate.postForObject(CREATE_ENROLLMENT_ENDPOINT_URL, enrollUser, EnrollUser.class);
		System.out.println(result);

	}

	private void updateEnrollment() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		EnrollUser enrollment = new EnrollUser();
		enrollment.setName("SandeepUpdated");
		enrollment.setPhoneNumber("1234567890");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_ENROLLMENT_ENDPOINT_URL, enrollment, params);
	}

	private void deleteEnrollment() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_ENROLLMENT_ENDPOINT_URL, params);
	}
}

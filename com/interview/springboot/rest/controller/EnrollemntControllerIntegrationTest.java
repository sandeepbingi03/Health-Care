package com.interview.springboot.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.interview.springboot.rest.Application;
import com.interview.springboot.rest.model.Dependent;
import com.interview.springboot.rest.model.EnrollUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnrollemntControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {}

	@Test
	public void testGetAllEnrollments() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/enroll",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEnrollemntById() {
		EnrollUser enrollment = restTemplate.getForObject(getRootUrl() + "/enroll/1", EnrollUser.class);
		System.out.println(enrollment.getName());
		assertNotNull(enrollment);
	}

	@Test
	public void testCreateEnrollemnt() {
		EnrollUser enroll = new EnrollUser();
		enroll.setName("Sandeep");
		enroll.setBirthDate(new Date(622790105000L));
		enroll.setActivationStatus(true);
		enroll.setPhoneNumber("9000000000");
		List<Dependent> dependents = new ArrayList<Dependent>();
		Dependent dependent1 = new Dependent();
		dependent1.setName("SandeepFather");
		dependent1.setBirthDate(new Date(622790105000L));
		dependents.add(dependent1 );
		enroll.setList(dependents );

		ResponseEntity<EnrollUser> postResponse = restTemplate.postForEntity(getRootUrl() + "/enroll", enroll, EnrollUser.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEnrollemnt() {
		int id = 1;
		EnrollUser enroll = restTemplate.getForObject(getRootUrl() + "/enroll/" + id, EnrollUser.class);
		enroll.setName("Sandeep1");
		enroll.setPhoneNumber("9001234567");

		restTemplate.put(getRootUrl() + "/enroll/" + id, enroll);

		EnrollUser updatedEnrollemnt = restTemplate.getForObject(getRootUrl() + "/enroll/" + id, EnrollUser.class);
		assertNotNull(updatedEnrollemnt);
	}

	@Test
	public void testDeleteEnrollemnt() {
		int id = 2;
		EnrollUser enroll = restTemplate.getForObject(getRootUrl() + "/enroll/" + id, EnrollUser.class);
		assertNotNull(enroll);

		restTemplate.delete(getRootUrl() + "/enroll/" + id);

		try {
			enroll = restTemplate.getForObject(getRootUrl() + "/enroll/" + id, EnrollUser.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}

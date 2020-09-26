package com.interview.springboot.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.springboot.rest.exception.ResourceNotFoundException;
import com.interview.springboot.rest.model.Dependent;
import com.interview.springboot.rest.model.EnrollUser;
import com.interview.springboot.rest.repository.EnrollmentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EnrollmentController {
	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@GetMapping("/enroll")
	public List<EnrollUser> getAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	@GetMapping("/enroll/{id}")
	public ResponseEntity<EnrollUser> getEnrollmentById(@PathVariable(value = "id") Long enrollId)
			throws ResourceNotFoundException {
		EnrollUser enroll = enrollmentRepository.findById(enrollId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollId));
		return ResponseEntity.ok().body(enroll);
	}

	@PostMapping("/enroll")
	public EnrollUser enrollUser(@Valid @RequestBody EnrollUser enroll) {
		return enrollmentRepository.save(enroll);
	}

	@PutMapping("/enroll/{id}")
	public ResponseEntity<EnrollUser> updateEnrollment(@PathVariable(value = "id") Long enrollId,
			@Valid @RequestBody EnrollUser enrollUser) throws ResourceNotFoundException {
		EnrollUser enrollEntity = enrollmentRepository.findById(enrollId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollId));

		enrollEntity.setName(enrollUser.getName());
		enrollEntity.setBirthDate(enrollUser.getBirthDate());
		enrollEntity.setActivationStatus(enrollUser.isActivationStatus());
		enrollEntity.setList(enrollUser.getList());
		final EnrollUser enrollmentUpdate = enrollmentRepository.save(enrollEntity);
		return ResponseEntity.ok(enrollmentUpdate);
	}

	@DeleteMapping("/enroll/{id}")
	public Map<String, Boolean> deleteEnrollment(@PathVariable(value = "id") Long enrollId)
			throws ResourceNotFoundException {
		EnrollUser enrollUser = enrollmentRepository.findById(enrollId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollId));

		enrollmentRepository.delete(enrollUser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

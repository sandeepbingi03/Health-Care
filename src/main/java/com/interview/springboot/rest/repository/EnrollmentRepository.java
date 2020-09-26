package com.interview.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.springboot.rest.model.EnrollUser;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollUser, Long>{

}

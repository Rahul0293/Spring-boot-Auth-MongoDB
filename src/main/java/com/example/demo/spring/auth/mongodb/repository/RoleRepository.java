package com.example.demo.spring.auth.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.spring.auth.mongodb.models.ERole;
import com.example.demo.spring.auth.mongodb.models.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	
	
	Optional<Role> findByName(ERole name);

}

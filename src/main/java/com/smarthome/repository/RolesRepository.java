package com.smarthome.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.smarthome.models.Role;

@Repository
public interface RolesRepository extends MongoRepository<Role, Integer>{

	public Role findByroleName(String roleName); 
	public Role findByroleId(String roleId);
	
}

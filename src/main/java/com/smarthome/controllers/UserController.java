package com.smarthome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.Services.UserService;
import com.smarthome.models.GenericResponse;
import com.smarthome.models.Role;
import com.smarthome.models.User;

@RestController
@RequestMapping("/user-api")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user")
	public GenericResponse registerUser(@RequestBody User user) {
		
		return userService.registerUser(user);
		
	}
	
	@PutMapping("/user/password")
	public GenericResponse changePassword(@RequestBody User user) {
		
		return userService.changePassword(user);
		
	}
	
	@PutMapping("/user")
	public GenericResponse editUserDetails(@RequestBody User user) {
		
		return userService.editUserDetails(user);
		
	}
	
	@GetMapping("/user")
	public GenericResponse loginUser(@RequestBody User user) {
		
		return userService.loginUser(user);
		
	}
	
	@GetMapping("/roles")
	public GenericResponse getAllRole() {
		
		return userService.getAllRole();
		
	}
	
	@PostMapping("/role")
	public GenericResponse createRole(@RequestBody Role userRole) {
	
		return userService.createRole(userRole);
		
	}
	
	@PutMapping("/role")
	public GenericResponse editRole(@RequestBody Role userRole) {
	
		return userService.editRole(userRole);
		
	}
	
	@DeleteMapping("/role")
	public GenericResponse deleteRole(@RequestBody Role userRole) {
	
		return userService.deleteRole(userRole);
		
	}
	
	@GetMapping("/users")
	public GenericResponse getAllUserDetails() {
	
		return userService.getAllUserDetails();
		
	}
	
	@GetMapping("user/id/{id}")
	public GenericResponse getUserById(@PathVariable("id") String userId) {
	
		return userService.getUserById(userId);
		
	}
	
}

package com.smarthome.Services;

import org.springframework.stereotype.Service;

import com.smarthome.models.GenericResponse;
import com.smarthome.models.Role;
import com.smarthome.models.User;

@Service
public interface UserService {

	public GenericResponse registerUser(User user);
	public GenericResponse changePassword(User user);
	public GenericResponse editUserDetails(User user);
	public GenericResponse loginUser(User user);
	public GenericResponse getAllRole();
	public GenericResponse createRole(Role userRole);
	public GenericResponse editRole(Role userRole);
	public GenericResponse deleteRole(Role userRole);
	public GenericResponse getAllUserDetails();
	public GenericResponse getUserById(String userId);
}

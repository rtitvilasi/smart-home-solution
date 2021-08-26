package com.smarthome.Services;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smarthome.exception.SmartNotFoundException;
import com.smarthome.models.GenericResponse;
import com.smarthome.models.Role;
import com.smarthome.models.User;
import com.smarthome.repository.RolesRepository;
import com.smarthome.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RolesRepository rolesRepo;
	
	@Override
	public GenericResponse registerUser(User user) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
		try {
			
			User userexist = userRepo.findByemail(user.getEmail());
			if(userexist!=null) {
				throw new SmartNotFoundException("Email already exist Try with another Email");
			}
			
			if(user.getEmail()==null) {
				throw new SmartNotFoundException("Email is blank");
			}
			if(user.getName()==null) {
				throw new SmartNotFoundException("name is blank");
			}
			if(user.getContact()==null) {
				throw new SmartNotFoundException("Contact is blank");
			}
			if(user.getPassword()==null) {
				throw new SmartNotFoundException("password is blank");
			}
			Role userRoles = rolesRepo.findByroleName("client");
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			random.nextBytes(salt);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			user.setPasswordHash(hash);
			user.setPassword("hashed");
			user.setUserRole(userRoles);	
			userRepo.save(user);
			genericResponse.setValid(true);
			return genericResponse;
		}
		catch(Exception ex) {
			throw new SmartNotFoundException(ex.toString());
		}
	}

	@Override
	public GenericResponse changePassword(User user) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
		try {
			
			if(user.getUserId()==null) {
				throw new SmartNotFoundException("User Id is blank");
			}
			if(user.getPassword()==null) {
				throw new SmartNotFoundException("Password is blank");
			}
			User userexist = userRepo.findByuserId(user.getUserId());
			
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			random.nextBytes(salt);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			userexist.setPasswordHash(hash);	
			userRepo.save(userexist);
			genericResponse.setValid(true);
			return genericResponse;
		}
		catch(Exception ex) {
			throw new SmartNotFoundException(ex.toString());
		}
	}

	@Override
	public GenericResponse editUserDetails(User user) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
			if(user.getEmail()==null) {
				throw new SmartNotFoundException("Email is blank");
			}
			if(user.getName()==null) {
				throw new SmartNotFoundException("name is blank");
			}
			if(user.getContact()==null) {
				throw new SmartNotFoundException("Contact is blank");
			}
			if(user.getUserId()==null) {
				throw new SmartNotFoundException("User Id is blank");
			}
			
			User userexist = userRepo.findByuserId(user.getUserId());
			
			userexist.setName(user.getName());
			userexist.setContact(user.getContact());
			userexist.setEmail(user.getEmail());
			userRepo.save(userexist);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse loginUser(User user) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		try {
			if(user.getEmail()==null) {
				throw new SmartNotFoundException("Email is blank");
			}
			if(user.getPassword()==null) {
				throw new SmartNotFoundException("Password is blank");
			}
			
			User userexist = userRepo.findByemail(user.getEmail());
			if(userexist==null) {
				throw new SmartNotFoundException("Email does not exist");
			}
			
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			random.nextBytes(salt);
			byte[] hash = factory.generateSecret(spec).getEncoded();
			//hash.toString();
			if(!CompareHash(userexist.getPasswordHash(), hash)) {
				throw new SmartNotFoundException("Password does not match the  given password");
			}
			
			
			User tmpUser = new User();
			tmpUser.setUserId(userexist.getUserId());
			tmpUser.setName(userexist.getName());
			tmpUser.setEmail(userexist.getEmail());
			tmpUser.setContact(userexist.getContact());
			Role tmpRoles = new Role();
			tmpRoles.setRoleName(userexist.getUserRole().getRoleName());
			tmpUser.setUserRole(tmpRoles);
			genericResponse.setValid(true);
			genericResponse.setResponseBody(tmpUser);
			return genericResponse;
		}
		catch(Exception ex){
			throw new SmartNotFoundException(ex.toString());
		}
	}

	private boolean CompareHash(byte[] one, byte[] two) {
		if(one.length!=two.length) {
			return false;
		}
		else {
			for(int i=0;i<one.length;i++) {
				if(one[i]!=two[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public GenericResponse getAllRole() throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();

			List<Role> allRoles = rolesRepo.findAll();
			genericResponse.setValid(true);
			genericResponse.setResponseBody(allRoles);
			return genericResponse;
	}

	@Override
	public GenericResponse createRole(Role userRole) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
			if(userRole.getRoleName()==null) {
				throw new SmartNotFoundException("Role Name is Blank");
			}
			Role exists = rolesRepo.findByroleName(userRole.getRoleName());
			if(exists!=null) {
				throw new SmartNotFoundException("User Role Already Exists");
			}
			rolesRepo.save(userRole);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse editRole(Role userRole) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
			if(userRole.getRoleName()==null) {
				throw new SmartNotFoundException("Role Name is Blank");
			}
			Role exists = rolesRepo.findByroleId(userRole.getRoleId());
			if(exists==null) {
				throw new SmartNotFoundException("User Role does not Exists");
			}
			exists.setRoleName(userRole.getRoleName());
			rolesRepo.save(exists);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse deleteRole(Role userRole) throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();
		
			if(userRole.getRoleId()==null) {
				throw new SmartNotFoundException("Role Id is Blank");
			}
			
			Role exists = rolesRepo.findByroleId(userRole.getRoleId());
			if(exists==null) {
				throw new SmartNotFoundException("User Role does not Exists");
			}
			rolesRepo.delete(exists);
			genericResponse.setValid(true);
			return genericResponse;
	}

	@Override
	public GenericResponse getAllUserDetails() throws SmartNotFoundException {
		
		GenericResponse genericResponse = new GenericResponse();

			List<User> allUsers = userRepo.findAll();
			allUsers.stream().forEach(user->  {user.setPassword(null);user.setPasswordHash(null);});
			genericResponse.setValid(true);
			genericResponse.setResponseBody(allUsers);
			return genericResponse;

	}

	@Override
	public GenericResponse getUserById(String userId) throws SmartNotFoundException{
		
		GenericResponse genericResponse = new GenericResponse();
		
		
			if(userId==null) {
				 throw new SmartNotFoundException("user Id is Blank");
			}
			
			User tmpUser = userRepo.findByuserId(userId);
			tmpUser.setPassword(null);
			tmpUser.setPasswordHash(null);
			genericResponse.setValid(true);
			genericResponse.setResponseBody(tmpUser);
			return genericResponse;

		
	}

}

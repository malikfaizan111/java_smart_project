package com.faizan.smart.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.faizan.smart.dtos.AdminDTO;
import com.faizan.smart.dtos.UserDTO;
import com.faizan.smart.entities.Admin;
import com.faizan.smart.entities.User;
import com.faizan.smart.repositories.AdminRepository;
import com.faizan.smart.repositories.UserRepository;
import com.faizan.smart.utils.DuplicateUserException;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public AdminDTO getAdmin(Long id) {
		Admin admin = adminRepository.findById(id).orElse(null);
		return admin != null ? mapToDTO(admin) : null;
	}

	@Override
	@Transactional
	public void updateAdmin(AdminDTO adminDTO) {
		try {
			Admin admin = mapToEntity(adminDTO);
			adminRepository.save(admin);
		} catch (Exception e) {
			throw new RuntimeException("Failed to update admin", e);
		}
	}

	@Override
	@Transactional
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

	@Override
	@Transactional
	public AdminDTO save(AdminDTO adminDTO) {
	    try {
	        UserDTO userDTO = adminDTO.getUser();
	        if (userDTO != null) {
	            // Check if email or username already exists
	            User existingUserWithEmail = userRepository.findByEmail(userDTO.getEmail());
	            User existingUserWithUsername = userRepository.findByUsername(userDTO.getUsername());
	            if (existingUserWithEmail != null || existingUserWithUsername != null) {
	                throw new DuplicateUserException("User with the same email or username already exists.");
	            }

	            // Validate name
	            if (adminDTO.getName() == null || adminDTO.getName().isEmpty()) {
	                throw new IllegalArgumentException("Name is required.");
	            }

	            // Create user entity
	            User user = mapToEntity(userDTO);

	            // Create admin entity and associate with the user
	            Admin admin = mapToEntity(adminDTO);
	            admin.setUser(user);

	            // Save admin entity, which will also cascade save the associated user
	            Admin savedAdmin = adminRepository.save(admin);
	            return mapToDTO(savedAdmin);
	        } else {
	            throw new IllegalArgumentException("User Detail is Missing.");
	        }
	    } catch (DuplicateUserException | IllegalArgumentException e) {
	        // No need to re-throw, just propagate the exception
	        throw e;
	    } catch (DataAccessException e) {
	        throw new RuntimeException("Failed to save admin and user due to database error", e);
	    } catch (Exception e) {
	        throw new RuntimeException("Unexpected error occurred while saving admin and user", e);
	    }
	}


	private AdminDTO mapToDTO(Admin admin) {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setAdminId(admin.getAdmin_id());
		adminDTO.setName(admin.getName());
		adminDTO.setIsActive(admin.getIsActive());
		adminDTO.setPhoneNumber(admin.getPhoneNumber());
		adminDTO.setAddress(admin.getAddress());
		adminDTO.setUser(mapToDTO(admin.getUser()));
		return adminDTO;
	}

	private UserDTO mapToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUser_id());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}

	private Admin mapToEntity(AdminDTO adminDTO) {
		Admin admin = new Admin();
		admin.setAdmin_id(adminDTO.getAdminId());
		admin.setName(adminDTO.getName());
		admin.setIsActive(adminDTO.getIsActive());
		admin.setPhoneNumber(adminDTO.getPhoneNumber());
		admin.setAddress(adminDTO.getAddress());
		admin.setUser(mapToEntity(adminDTO.getUser()));
		return admin;
	}

	private User mapToEntity(UserDTO userDTO) {
		User user = new User();
		user.setUser_id(userDTO.getUserId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		return user;
	}

	@Override
	public List<AdminDTO> getAdmins() {
		List<Admin> admins = adminRepository.findAll();
		return admins.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
}
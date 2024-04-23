package com.faizan.smart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizan.smart.dtos.AdminDTO;
import com.faizan.smart.dtos.ErrorResponse;
import com.faizan.smart.entities.Admin;
import com.faizan.smart.services.AdminService;
import com.faizan.smart.services.UserService;
import com.faizan.smart.utils.DuplicateUserException;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveAdmin(@RequestBody AdminDTO adminDTO) {
		try {
			AdminDTO savedAdminDTO = adminService.save(adminDTO);
			return new ResponseEntity<>(savedAdminDTO, HttpStatus.CREATED);
		} catch (DuplicateUserException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to save admin and user.");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		List<AdminDTO> admins = adminService.getAdmins();
		if (!admins.isEmpty()) {
			return new ResponseEntity<>(admins, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) {
		AdminDTO admin = adminService.getAdmin(id);
		if (admin != null) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateAdmin(@RequestBody AdminDTO adminDTO) {
		adminService.updateAdmin(adminDTO);
		return new ResponseEntity<>("Admin updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
	    try {
	        // Delete admin
	        adminService.deleteAdmin(id);

	        // Retrieve associated user and delete it
	        AdminDTO admin = adminService.getAdmin(id);
	        if (admin != null && admin.getUser() != null) {
	            userService.deleteUser(admin.getUser().getUserId());
	        }

	        return new ResponseEntity<>("Admin and associated user deleted successfully", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to delete admin and associated user", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}

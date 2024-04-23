package com.faizan.smart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizan.smart.dtos.UserDTO;
import com.faizan.smart.entities.User;
import com.faizan.smart.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired UserService userservice;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getUser(){
		List<UserDTO> user = userservice.getUsers();
		if (!user.isEmpty()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

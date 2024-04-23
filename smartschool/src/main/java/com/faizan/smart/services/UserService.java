package com.faizan.smart.services;

import java.util.List;

import com.faizan.smart.dtos.UserDTO;
import com.faizan.smart.entities.User;

public interface UserService {

    Long save(UserDTO userDTO);
    
    List<UserDTO> getUsers();
    
    UserDTO getUser(Long id);
    
    void updateUser(UserDTO userDTO);
    
    void deleteUser(Long id);
    
    UserDTO findByEmail(String email);
    
    UserDTO findByUsername(String username);
}

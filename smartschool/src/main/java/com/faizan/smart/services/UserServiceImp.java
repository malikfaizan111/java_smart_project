package com.faizan.smart.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faizan.smart.dtos.UserDTO;
import com.faizan.smart.entities.Admin;
import com.faizan.smart.entities.User;
import com.faizan.smart.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Long save(UserDTO userDTO) {
        try {
            User user = mapToEntity(userDTO);
            return userRepository.save(user).getUser_id();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    @Transactional
    public List<UserDTO> getUsers() {
        List<User> users =  userRepository.findAll();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        try {
            User user = mapToEntity(userDTO);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }

    @Override
    @Transactional
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? mapToDTO(user) : null;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? mapToDTO(user) : null;
    }

    @Override
    @Transactional
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? mapToDTO(user) : null;
    }

    // Utility method to map Entity to DTO
    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    // Utility method to map DTO to Entity
    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUser_id(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}

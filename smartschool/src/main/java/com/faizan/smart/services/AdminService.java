package com.faizan.smart.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.faizan.smart.dtos.AdminDTO;

import jakarta.validation.Valid;

@Validated
public interface AdminService {
    
    AdminDTO save(@Valid AdminDTO adminDTO);
    
    List<AdminDTO> getAdmins();
    
    AdminDTO getAdmin(Long id);
    
    void updateAdmin(@Valid AdminDTO adminDTO);
    
    void deleteAdmin(Long id);
}

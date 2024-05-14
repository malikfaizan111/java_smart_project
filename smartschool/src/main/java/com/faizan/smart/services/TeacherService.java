package com.faizan.smart.services;

import com.faizan.smart.dtos.AdminDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TeacherService {

    AdminDTO save(@Valid AdminDTO adminDTO);

    List<AdminDTO> getAdmins();

    AdminDTO getAdmin(Long id);

    void updateAdmin(@Valid AdminDTO adminDTO);

    void deleteAdmin(Long id);
}

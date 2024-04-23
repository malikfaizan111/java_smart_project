package com.faizan.smart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faizan.smart.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}

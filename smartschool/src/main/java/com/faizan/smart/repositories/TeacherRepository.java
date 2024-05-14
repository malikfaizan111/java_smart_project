package com.faizan.smart.repositories;

import com.faizan.smart.entities.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher findByName(String name);
}
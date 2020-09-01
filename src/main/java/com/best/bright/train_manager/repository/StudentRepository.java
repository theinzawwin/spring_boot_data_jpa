package com.best.bright.train_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best.bright.train_manager.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}

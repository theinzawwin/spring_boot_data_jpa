package com.best.bright.train_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.best.bright.train_manager.dto.StudentInfo;
import com.best.bright.train_manager.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	public List<Student> findByNameLike(String name);
	
	public List<Student> findByRollNo(String rollNo);
	
	public List<Student> findByAgeBetween(int from, int to);
	
	@Query("select s from Student s where s.name=?1")
	Student findByName(String name);
	@Query("select s from Student s")
	public List<StudentInfo> findStudentList();
}

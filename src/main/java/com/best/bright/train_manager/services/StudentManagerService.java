package com.best.bright.train_manager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.bright.train_manager.dto.StudentInfo;
import com.best.bright.train_manager.dto.StudentRegisterDTO;
import com.best.bright.train_manager.model.Student;
import com.best.bright.train_manager.repository.StudentRepository;
@Service
public class StudentManagerService implements StudentManagerInterface{
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void saveStudent(StudentRegisterDTO stdDTO) {
		// TODO Auto-generated method stub
		studentRepository.save(convertStudent(stdDTO));
	}

	@Override
	public List<StudentRegisterDTO> getAllStudentList() {
		// TODO Auto-generated method stub
		List<Student> stdList=studentRepository.findAll();
		List<StudentRegisterDTO> dtoList=stdList.stream().map(s->convertStudentDTO(s)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<StudentInfo> getStudentInfo() {
		// TODO Auto-generated method stub
		return studentRepository.findStudentList();
	}
	Student convertStudent(StudentRegisterDTO dto) {
		Student std=new Student();
		std.setName(dto.getName());
		std.setRollNo(dto.getRollNo());
		std.setPhoneNo(dto.getPhoneNo());
		return std;
	}
	StudentRegisterDTO convertStudentDTO(Student dto) {
		StudentRegisterDTO std=new StudentRegisterDTO();
		std.setName(dto.getName());
		std.setRollNo(dto.getRollNo());
		std.setPhoneNo(dto.getPhoneNo());
		
		return std;
	}
}

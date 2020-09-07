package com.best.bright.train_manager.services;

import java.util.List;

import com.best.bright.train_manager.dto.StudentInfo;
import com.best.bright.train_manager.dto.StudentRegisterDTO;

public interface StudentManagerInterface {

	void saveStudent(StudentRegisterDTO stdDTO);
	List<StudentRegisterDTO> getAllStudentList();
	List<StudentInfo> getStudentInfo();
}

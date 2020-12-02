package com.app.sapient.grade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.mapper.StudentMapper;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Transactional
	public StudentDto addStudent(StudentDto studentDto) {
		return studentMapper.studentToStudentDto(studentRepository.save(studentMapper.studentDtoToStudent(studentDto)));
	}

	public StudentDto getStudentById(Long studentId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		if(optionalStudent.isPresent()) {
			return studentMapper.studentToStudentDto(optionalStudent.get());			
		} else {
			throw new StudentNotFountException();
		}
	}
	
}

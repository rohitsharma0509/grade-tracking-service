package com.app.sapient.grade.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.mapper.StudentMapper;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	@InjectMocks
	private StudentService studentService;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private StudentMapper studentMapper;

	@Test
	void testAddStudent() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(studentMapper.studentDtoToStudent(Mockito.any(StudentDto.class))).thenReturn(student);		
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentMapper.studentToStudentDto(Mockito.any(Student.class))).thenReturn(studentDto);
		StudentDto result = studentService.addStudent(new StudentDto());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test
	void testGetStudentByIdForNoRecord() {
		Mockito.when(studentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.ofNullable(null));
		Assertions.assertThrows(StudentNotFountException.class, () -> studentService.getStudentById(1L));
	}
	
	@Test
	void testGetStudentByIdForSuccessCase() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(studentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(student));
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentMapper.studentToStudentDto(Mockito.any(Student.class))).thenReturn(studentDto);
		StudentDto result = studentService.getStudentById(1L);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}

package com.app.sapient.grade.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
	@InjectMocks
	private StudentController studentController;
	
	@Mock
	private StudentService studentService;
	
	@Test
	public void testAddStudentForException() {
		Mockito.when(studentService.addStudent(Mockito.any(StudentDto.class))).thenThrow(new NullPointerException());
		ResponseEntity<StudentDto> result = studentController.addStudent(new StudentDto());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	public void testAddStudentForSuccessCase() {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentService.addStudent(Mockito.any(StudentDto.class))).thenReturn(studentDto);
		ResponseEntity<StudentDto> result = studentController.addStudent(studentDto);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}
	
	@Test
	public void testGetStudentDetailsForException() {
		Mockito.when(studentService.getStudentById(Mockito.eq(1L))).thenThrow(new NullPointerException());
		ResponseEntity<StudentDto> result = studentController.getStudentDetails(1L);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	public void testGetStudentDetailsForBadRequest() {
		Mockito.when(studentService.getStudentById(Mockito.eq(1L))).thenThrow(new StudentNotFountException());
		ResponseEntity<StudentDto> result = studentController.getStudentDetails(1L);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void testGetStudentDetailsForSuccessCase() {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentService.getStudentById(Mockito.eq(1L))).thenReturn(studentDto);
		ResponseEntity<StudentDto> result = studentController.getStudentDetails(1L);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}
}
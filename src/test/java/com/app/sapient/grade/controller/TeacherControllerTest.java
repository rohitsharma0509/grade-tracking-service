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

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.service.TeacherService;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {
	
	@InjectMocks
	private TeacherController teacherController;
	
	@Mock
	private TeacherService teacherService;
	
	@Test
	public void testConfigureTeacherForSuccessCase() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.configureTeacher(Mockito.any(TeacherDto.class))).thenReturn(teacherDto);
		ResponseEntity<TeacherDto> result = teacherController.configureTeacher(teacherDto);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}

	
	@Test
	public void testConfigureTeacherForException() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.configureTeacher(Mockito.any(TeacherDto.class))).thenThrow(new NullPointerException());
		ResponseEntity<TeacherDto> result = teacherController.configureTeacher(teacherDto);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	public void getTeacherByIdForBadRequest() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenThrow(new TeacherNotFoundException());
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void getTeacherByIdForException() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenThrow(new NullPointerException());
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	public void getTeacherByIdForSuccessCase() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenReturn(teacherDto);
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}

}

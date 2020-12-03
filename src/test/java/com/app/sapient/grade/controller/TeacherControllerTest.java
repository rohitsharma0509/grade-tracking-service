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
class TeacherControllerTest {
	
	@InjectMocks
	private TeacherController teacherController;
	
	@Mock
	private TeacherService teacherService;
	
	@Test
	void testConfigureTeacherForSuccessCase() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.configureTeacher(Mockito.any(TeacherDto.class))).thenReturn(teacherDto);
		ResponseEntity<TeacherDto> result = teacherController.configureTeacher(teacherDto);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}

	
	@Test
	void testConfigureTeacherForException() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.configureTeacher(Mockito.any(TeacherDto.class))).thenThrow(new NullPointerException());
		ResponseEntity<TeacherDto> result = teacherController.configureTeacher(teacherDto);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	void testGetTeacherByIdForBadRequest() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenThrow(new TeacherNotFoundException());
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	void testGetTeacherByIdForException() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenThrow(new NullPointerException());
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	void testGetTeacherByIdForSuccessCase() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenReturn(teacherDto);
		ResponseEntity<TeacherDto> result = teacherController.getTeacherById(1L);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}
	
	@Test
	void testDeleteTeacherByIdForException() {
		Mockito.doThrow(new NullPointerException()).when(teacherService).deleteTeacherById(1L);
		ResponseEntity<Void> result = teacherController.deleteTeacherById(1L);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	@Test
	void testDeleteTeacherByIdForSuccessCase() {
		ResponseEntity<Void> result = teacherController.deleteTeacherById(1L);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Mockito.verify(teacherService, Mockito.times(1)).deleteTeacherById(1L);
	}

}

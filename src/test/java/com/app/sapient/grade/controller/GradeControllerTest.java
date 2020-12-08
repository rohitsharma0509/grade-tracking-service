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

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.service.GradeService;

@ExtendWith(MockitoExtension.class)
class GradeControllerTest {

	@InjectMocks
	private GradeController gradeController;

	@Mock
	private GradeService gradeService;

	@Test
	void testAddGradeItemForNull() {
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	void testAddGradeItemForNullTeacherId() {
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setStudentId(1L);
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(gradeItemDto);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	void testAddGradeItemForNullStudentId() {
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(gradeItemDto);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	void testAddGradeItemWhenExceptionOccurs() {
		Mockito.when(gradeService.addNewGradeItem(Mockito.any(GradeItemDto.class))).thenThrow(new NullPointerException());
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		gradeItemDto.setStudentId(1L);
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(gradeItemDto);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}

	@Test
	void testAddGradeItemWhenTeacherNotFoundWithGivenTeacherId() {
		Mockito.when(gradeService.addNewGradeItem(Mockito.any(GradeItemDto.class))).thenThrow(new TeacherNotFoundException());
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		gradeItemDto.setStudentId(1L);
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(gradeItemDto);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	void testAddGradeItemForSuccessCase() {
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setId(1L);
		gradeItemDto.setTeacherId(1L);
		gradeItemDto.setStudentId(1L);
		Mockito.when(gradeService.addNewGradeItem(Mockito.any(GradeItemDto.class))).thenReturn(gradeItemDto);
		ResponseEntity<GradeItemDto> result = gradeController.addGradeItem(gradeItemDto);
		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assert.assertEquals(Long.valueOf(1L), result.getBody().getId());
	}

}

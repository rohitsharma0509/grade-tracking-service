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

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.mapper.TeacherMapper;
//import com.app.sapient.grade.mapper.TeacherMapper;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
	
	@InjectMocks
	private TeacherService teacherService;
	
	@Mock
	private TeacherRepository teacherRepository;
	
	@Mock
	private TeacherMapper teacherMapper;

	@Test
	void testConfigureTeacher() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherMapper.toTeacher(Mockito.any(TeacherDto.class))).thenReturn(teacher);		
		Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherMapper.toTeacherDto(Mockito.any(Teacher.class))).thenReturn(teacherDto);
		TeacherDto result = teacherService.configureTeacher(new TeacherDto());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test
	void testGetTeacherByIdForNoRecord() {
		Mockito.when(teacherRepository.findById(Mockito.eq(1L))).thenReturn(Optional.ofNullable(null));
		Assertions.assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacherById(1L));
	}
	
	@Test
	void testGetTeacherByIdForSuccessCase() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(teacher));
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherMapper.toTeacherDto(Mockito.any(Teacher.class))).thenReturn(teacherDto);
		TeacherDto result = teacherService.getTeacherById(1L);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test
	void testDeleteTeacherByIdForSuccessCase() {
		teacherService.deleteTeacherById(1L);
		Mockito.verify(teacherRepository, Mockito.times(1)).deleteById(1L);
	}

}

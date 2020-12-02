package com.app.sapient.grade.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.mapper.TeacherMapper;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.TeacherRepository;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {
	
	@InjectMocks
	private TeacherService teacherService;
	
	@Mock
	private TeacherRepository teacherRepository;
	
	@Mock
	private TeacherMapper teacherMapper;

	@Test
	public void testConfigureTeacher() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherMapper.teacherDtoToTeacher(Mockito.any(TeacherDto.class))).thenReturn(teacher);		
		Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherMapper.teacherToTeacherDto(Mockito.any(Teacher.class))).thenReturn(teacherDto);
		TeacherDto result = teacherService.configureTeacher(new TeacherDto());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test(expected = TeacherNotFoundException.class)
	public void testGetTeacherByIdForNoRecord() {
		Mockito.when(teacherRepository.findById(Mockito.eq(1L))).thenReturn(Optional.ofNullable(null));
		teacherService.getTeacherById(1L);
	}
	
	@Test
	public void testGetTeacherByIdForSuccessCase() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(teacher));
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Mockito.when(teacherMapper.teacherToTeacherDto(Mockito.any(Teacher.class))).thenReturn(teacherDto);
		TeacherDto result = teacherService.getTeacherById(1L);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}

package com.app.sapient.grade.mapper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.model.Teacher;

@ExtendWith(MockitoExtension.class)
public class TeacherMapperTest {
	
	@InjectMocks
	private TeacherMapper teacherMapper;

	@Test
	public void testTeacherToTeacherDtoForNull() {
		TeacherDto result = teacherMapper.teacherToTeacherDto(null);
		Assert.assertNull(result);
	}
	
	@Test
	public void testTeacherToTeacherDto() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		TeacherDto result = teacherMapper.teacherToTeacherDto(teacher);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test
	public void testTeacherDtoToTeacherForNull() {
		Teacher result = teacherMapper.teacherDtoToTeacher(null);
		Assert.assertNull(result);
	}
	
	@Test
	public void testTeacherDtoToTeacher() {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(1L);
		Teacher result = teacherMapper.teacherDtoToTeacher(teacherDto);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}

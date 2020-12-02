package com.app.sapient.grade.mapper;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.StudentRepository;
import com.app.sapient.grade.repository.TeacherRepository;

@RunWith(MockitoJUnitRunner.class)
public class GradeItemMapperTest {

	@InjectMocks
	private GradeItemMapper gradeItemMapper;

	@Mock
	private TeacherRepository teacherRepository;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentMapper studentMapper;

	@Test(expected = TeacherNotFoundException.class)
	public void testGradeItemDtoToGradeItemWhenTeacherNotFound() {
		Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		gradeItemMapper.gradeItemDtoToGradeItem(gradeItemDto);
	}

	@Test(expected = StudentNotFountException.class)
	public void testGradeItemDtoToGradeItemWhenStudentNotFound() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
		Mockito.when(studentRepository.findById(100L)).thenReturn(Optional.ofNullable(null));
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		StudentDto studentDto = new StudentDto();
		studentDto.setId(100L);
		gradeItemDto.setStudentDto(studentDto);
		gradeItemMapper.gradeItemDtoToGradeItem(gradeItemDto);
	}

	@Test
	public void testGradeItemDtoToGradeItemForNull() {
		GradeItem result = gradeItemMapper.gradeItemDtoToGradeItem(null);
		Assert.assertNull(result);
		Mockito.verifyNoInteractions(teacherRepository, studentRepository, studentMapper);
	}

	@Test
	public void testGradeItemDtoToGradeItemForSuccessCase() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
		Student student = new Student();
		Mockito.when(studentRepository.findById(100L)).thenReturn(Optional.of(student));
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setId(1L);
		gradeItemDto.setTeacherId(1L);
		StudentDto studentDto = new StudentDto();
		studentDto.setId(100L);
		gradeItemDto.setStudentDto(studentDto);
		GradeItem result = gradeItemMapper.gradeItemDtoToGradeItem(gradeItemDto);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}
	
	@Test
	public void testGradeItemToGradeItemDtoForNull() {
		GradeItemDto result = gradeItemMapper.gradeItemToGradeItemDto(null);
		Assert.assertNull(result);
		Mockito.verifyNoInteractions(teacherRepository, studentRepository, studentMapper);
	}
	
	@Test
	public void testGradeItemToGradeItemDtoForSuccessCase() {
		GradeItem gradeItem = new GradeItem();
		gradeItem.setId(1L);
		Student student = new Student();
		student.setId(1L);
		gradeItem.setStudent(student);
		Mockito.when(studentMapper.studentToStudentDto(Mockito.any(Student.class))).thenReturn(new StudentDto());
		GradeItemDto result = gradeItemMapper.gradeItemToGradeItemDto(gradeItem);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Mockito.verifyNoInteractions(teacherRepository, studentRepository);
	}

}

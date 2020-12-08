package com.app.sapient.grade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.factory.Assignment;
import com.app.sapient.grade.factory.GradeFactory;
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
	
	@Mock
	private GradeFactory gradeFactory;
	
	@Mock
	private Assignment assignment;

	@Test
	void testAddStudent() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(studentMapper.toStudent(Mockito.any(StudentDto.class))).thenReturn(student);
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class))).thenReturn(studentDto);
		StudentDto result = studentService.addStudent(new StudentDto());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Mockito.verifyNoInteractions(gradeFactory, assignment);
	}

	@Test
	void testGetStudentByIdForNoRecord() {
		Mockito.when(studentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.ofNullable(null));
		Assertions.assertThrows(StudentNotFountException.class, () -> studentService.getStudentById(1L));
		Mockito.verifyNoInteractions(gradeFactory, assignment);
	}
	
	@Test
	void testGetStudentByIdForSuccessCaseWithoutGradeItem() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(studentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(student));
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class))).thenReturn(studentDto);
		StudentDto result = studentService.getStudentById(1L);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Mockito.verifyNoInteractions(gradeFactory, assignment);
	}

	@Test
	void testGetStudentByIdForSuccessCase() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(studentRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(student));
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		List<GradeItemDto> gradeItemDtos = new ArrayList<>();
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDtos.add(gradeItemDto);
		studentDto.setGradeItemDtos(gradeItemDtos);
		Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class))).thenReturn(studentDto);
		Mockito.when(gradeFactory.getGradeStrategy(Mockito.any(GradeItemDto.class), Mockito.anyDouble())).thenReturn(assignment);
		Mockito.when(assignment.getGradeAsPercentage()).thenReturn(10.0);
		StudentDto result = studentService.getStudentById(1L);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}

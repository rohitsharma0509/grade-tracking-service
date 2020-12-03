package com.app.sapient.grade.mapper;

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
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.StudentRepository;
import com.app.sapient.grade.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
class GradeItemMapperTest {

	@InjectMocks
	private GradeItemMapper gradeItemMapper;

	@Mock
	private TeacherRepository teacherRepository;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentMapper studentMapper;
	
	@Test
	void testGradeItemsDtoToGradeItemsForNull() {
		List<GradeItem> result = gradeItemMapper.gradeItemDtosToGradeItems(null);
		Assert.assertEquals(0, result.size());
		Mockito.verifyNoInteractions(teacherRepository, studentRepository, studentMapper);
	}
	
	@Test
	void testGradeItemDtosToGradeItemsWhenTeacherNotFound() {
		Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		List<GradeItemDto> gradeItemDtos = new ArrayList<>();
		gradeItemDtos.add(gradeItemDto);
		Assertions.assertThrows(TeacherNotFoundException.class, () -> gradeItemMapper.gradeItemDtosToGradeItems(gradeItemDtos));
	}

	@Test
	void testGradeItemDtoToGradeItemsWhenStudentNotFound() {
		Teacher teacher = new Teacher();
		teacher.setId(1L);
		Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
		Mockito.when(studentRepository.findById(100L)).thenReturn(Optional.ofNullable(null));
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setTeacherId(1L);
		StudentDto studentDto = new StudentDto();
		studentDto.setId(100L);
		gradeItemDto.setStudentDto(studentDto);
		List<GradeItemDto> gradeItemDtos = new ArrayList<>();
		gradeItemDtos.add(gradeItemDto);
		Assertions.assertThrows(StudentNotFountException.class, () -> gradeItemMapper.gradeItemDtosToGradeItems(gradeItemDtos));
	}

	@Test
	void testGradeItemDtosToGradeItemsForSuccessCase() {
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
		List<GradeItemDto> gradeItemDtos = new ArrayList<>();
		gradeItemDtos.add(gradeItemDto);
		List<GradeItem> result = gradeItemMapper.gradeItemDtosToGradeItems(gradeItemDtos);
		Assert.assertEquals(Long.valueOf(1L), result.get(0).getId());
	}
	
	@Test
	void testGradeItemDtoToGradeItemForNull() {
		GradeItem result = gradeItemMapper.gradeItemDtoToGradeItem(null);
		Assert.assertNull(result);
		Mockito.verifyNoInteractions(teacherRepository, studentRepository, studentMapper);
	}
	
	@Test
	void testGradeItemsToGradeItemDtosForNull() {
		List<GradeItemDto> result = gradeItemMapper.gradeItemsToGradeItemDtos(null);
		Assert.assertEquals(0, result.size());
		Mockito.verifyNoInteractions(teacherRepository, studentRepository, studentMapper);
	}
	
	@Test
	void testGradeItemToGradeItemDtoForNull() {
		GradeItemDto result = gradeItemMapper.gradeItemToGradeItemDto(null);
		Assert.assertNull(result);
	}
	
	@Test
	void testGradeItemsToGradeItemDtosForSuccessCase() {
		GradeItem gradeItem = new GradeItem();
		gradeItem.setId(1L);
		Student student = new Student();
		student.setId(1L);
		gradeItem.setStudent(student);
		List<GradeItem> gradeItems = new ArrayList<>();
		gradeItems.add(gradeItem);
		Mockito.when(studentMapper.studentToStudentDto(Mockito.any(Student.class), Mockito.eq(false))).thenReturn(new StudentDto());
		List<GradeItemDto> result = gradeItemMapper.gradeItemsToGradeItemDtos(gradeItems);
		Assert.assertEquals(Long.valueOf(1L), result.get(0).getId());
		Mockito.verifyNoInteractions(teacherRepository, studentRepository);
	}

}

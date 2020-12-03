package com.app.sapient.grade.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.service.TeacherService;
import com.app.sapient.grade.type.GradeType;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {
	
	@InjectMocks
	private StudentMapper studentMapper;
	
	@Mock
	private GradeItemMapper gradeItemMapper;
	
	@Mock
	private TeacherService teacherService;

	@Test
	void testStudentDtoToStudentForNull() {
		Student result = studentMapper.studentDtoToStudent(null);
		Assert.assertNull(result);
		Mockito.verifyNoInteractions(gradeItemMapper, teacherService);
	}
	
	@Test
	void testStudentDtoToStudentForSuccessCase() {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		Student result = studentMapper.studentDtoToStudent(studentDto);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Mockito.verifyNoInteractions(gradeItemMapper, teacherService);
	}
	
	@Test
	void testStudentToStudentDtoWithoutGradeItems() {
		Student student = new Student();
		student.setId(1L);
		Mockito.when(gradeItemMapper.gradeItemsToGradeItemDtos(Mockito.any(), Mockito.eq(false))).thenReturn(null);
		StudentDto result = studentMapper.studentToStudentDto(student);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Mockito.verifyNoInteractions(teacherService);
	}
	
	@Test
	void testStudentToStudentDtoWithGradeItems() {
		Student student = new Student();
		student.setId(1L);
		List<GradeItem> gradeItems = new ArrayList<>();
		gradeItems.add(getGradeItem(1L, GradeType.ASSIGNMENT.toString(), 85.0, 100.0));
		gradeItems.add(getGradeItem(1L, GradeType.ASSIGNMENT.toString(), 88.0, 100.0));
		gradeItems.add(getGradeItem(1L, GradeType.EXTRA_CREDIT.toString(), null, null));
		gradeItems.add(getGradeItem(1L, GradeType.ASSIGNMENT.toString(), 92.0, 100.0));
		gradeItems.add(getGradeItem(1L, GradeType.EXAM.toString(), 91.0, 100.0));
		student.setGradeItems(gradeItems);
		
		Mockito.when(gradeItemMapper.gradeItemsToGradeItemDtos(Mockito.anyList(), Mockito.eq(false))).thenReturn(null);
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setAssignmentPercent(10.1);
		teacherDto.setExamPercent(89.9);
		teacherDto.setExtraCreditPercent(2.0);
		Mockito.when(teacherService.getTeacherById(1L)).thenReturn(teacherDto);
		StudentDto result = studentMapper.studentToStudentDto(student);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
		Assert.assertEquals(Double.valueOf(92.73), result.getGradeAsPercent());
	}
	
	private GradeItem getGradeItem(long id, String type, Double obtained, Double total) {
		GradeItem gradeItem = new GradeItem();
		gradeItem.setTeacherId(id);
		gradeItem.setType(type);
		gradeItem.setObtainedScore(obtained);
		gradeItem.setTotalScore(total);
		return gradeItem;
	}
}

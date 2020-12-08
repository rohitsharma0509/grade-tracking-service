package com.app.sapient.grade.factory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.service.TeacherService;
import com.app.sapient.grade.type.GradeType;

@ExtendWith(MockitoExtension.class)
class GradeFactoryTest {
	
	@InjectMocks
	private GradeFactory gradeFactory;
	
	@Mock
	private TeacherService teacherService;

	@Test
	void testGetGradeStrategyForInvalidType() {
		GradeStrategy result = gradeFactory.getGradeStrategy(new GradeItemDto(), 0.0);
		Assert.assertNull(result);
	}
	
	@Test
	void testGetGradeStrategyForAssignmentType() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenReturn(new TeacherDto());
		GradeStrategy result = gradeFactory.getGradeStrategy(getGradeItemDto(GradeType.ASSIGNMENT, 1L), 0.0);
		Assert.assertTrue(result instanceof Assignment);
	}
	
	@Test
	void testGetGradeStrategyForExamType() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenReturn(new TeacherDto());
		GradeStrategy result = gradeFactory.getGradeStrategy(getGradeItemDto(GradeType.EXAM, 1L), 0.0);
		Assert.assertTrue(result instanceof Exam);
	}
	
	@Test
	void testGetGradeStrategyForExtraCreditType() {
		Mockito.when(teacherService.getTeacherById(Mockito.eq(1L))).thenReturn(new TeacherDto());
		GradeStrategy result = gradeFactory.getGradeStrategy(getGradeItemDto(GradeType.EXTRA_CREDIT, 1L), 0.0);
		Assert.assertTrue(result instanceof ExtraCredit);
	}
	
	private GradeItemDto getGradeItemDto(GradeType gradeType, Long teacherId) {
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setType(gradeType.toString());
		gradeItemDto.setTeacherId(teacherId);
		return gradeItemDto;
	}

}

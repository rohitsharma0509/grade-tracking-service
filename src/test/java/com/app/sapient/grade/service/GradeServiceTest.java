package com.app.sapient.grade.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.mapper.GradeItemMapper;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.repository.GradeItemRepository;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

	@InjectMocks
	private GradeService gradeService;

	@Mock
	private GradeItemRepository gradeItemRepository;

	@Mock
	private GradeItemMapper gradeItemMapper;
	
	@Mock
	private StudentService studentService;

	@Test
	void testAddGradeItem() {
		GradeItem gradeItem = new GradeItem();
		gradeItem.setId(1L);
		Mockito.when(studentService.getStudentById(1L)).thenReturn(new StudentDto());
		Mockito.when(gradeItemMapper.toGradeItem(Mockito.any(GradeItemDto.class), Mockito.any(StudentDto.class))).thenReturn(gradeItem);
		Mockito.when(gradeItemRepository.save(Mockito.any(GradeItem.class))).thenReturn(gradeItem);
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setId(1L);
		gradeItemDto.setStudentId(1L);
		Mockito.when(gradeItemMapper.toGradeItemDto(Mockito.any(GradeItem.class))).thenReturn(gradeItemDto);
		GradeItemDto result = gradeService.addNewGradeItem(gradeItemDto);
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}

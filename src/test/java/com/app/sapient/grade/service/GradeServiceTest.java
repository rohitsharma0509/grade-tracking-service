package com.app.sapient.grade.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.mapper.GradeItemMapper;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.repository.GradeItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
	
	@InjectMocks
	private GradeService gradeService;
	
	@Mock
	private GradeItemRepository gradeItemRepository;
	
	@Mock
	private GradeItemMapper gradeItemMapper;

	@Test
	public void testAddGradeItem() {
		GradeItem gradeItem = new GradeItem();
		gradeItem.setId(1L);
		Mockito.when(gradeItemMapper.gradeItemDtoToGradeItem(Mockito.any(GradeItemDto.class))).thenReturn(gradeItem);		
		Mockito.when(gradeItemRepository.save(Mockito.any(GradeItem.class))).thenReturn(gradeItem);
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setId(1L);
		Mockito.when(gradeItemMapper.gradeItemToGradeItemDto(Mockito.any(GradeItem.class))).thenReturn(gradeItemDto);
		GradeItemDto result = gradeService.addGradeItem(new GradeItemDto());
		Assert.assertEquals(Long.valueOf(1L), result.getId());
	}

}
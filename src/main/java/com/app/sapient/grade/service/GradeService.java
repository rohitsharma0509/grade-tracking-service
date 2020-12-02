package com.app.sapient.grade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.mapper.GradeItemMapper;
import com.app.sapient.grade.repository.GradeItemRepository;

@Service
public class GradeService {

	@Autowired
	private GradeItemRepository gradeItemRepository;
	
	@Autowired
	private GradeItemMapper gradeItemMapper;
	
	public GradeItemDto addGradeItem(GradeItemDto gradeItemDto) {
		return gradeItemMapper.gradeItemToGradeItemDto(gradeItemRepository.save(gradeItemMapper.gradeItemDtoToGradeItem(gradeItemDto)));
	}
}

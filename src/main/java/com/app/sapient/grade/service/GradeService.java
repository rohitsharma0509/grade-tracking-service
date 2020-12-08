package com.app.sapient.grade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.mapper.GradeItemMapper;
import com.app.sapient.grade.repository.GradeItemRepository;

@Service
public class GradeService {

	private GradeItemRepository gradeItemRepository;
	private GradeItemMapper gradeItemMapper;
	private StudentService studentService;

	@Autowired
	public GradeService(GradeItemRepository gradeItemRepository, GradeItemMapper gradeItemMapper, StudentService studentService) {
		this.gradeItemRepository = gradeItemRepository;
		this.gradeItemMapper = gradeItemMapper;
		this.studentService = studentService;
	}

	public GradeItemDto addNewGradeItem(GradeItemDto gradeItemDto) {
		StudentDto studentDto = studentService.getStudentById(gradeItemDto.getStudentId());
		return gradeItemMapper.toGradeItemDto(gradeItemRepository.save(gradeItemMapper.toGradeItem(gradeItemDto, studentDto)));
	}

}

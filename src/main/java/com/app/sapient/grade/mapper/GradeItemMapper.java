package com.app.sapient.grade.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.StudentRepository;
import com.app.sapient.grade.repository.TeacherRepository;

@Component
public class GradeItemMapper {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	public List<GradeItem> gradeItemDtosToGradeItems(List<GradeItemDto> gradeItemDtos) {
		if(CollectionUtils.isEmpty(gradeItemDtos)) {
			return Collections.emptyList();
		}
		
		List<GradeItem> gradeItems = new ArrayList<>();
		gradeItemDtos.stream().forEach(gradeItemDto -> gradeItems.add(gradeItemDtoToGradeItem(gradeItemDto)));
		return gradeItems;
	}
	
	public GradeItem gradeItemDtoToGradeItem(GradeItemDto gradeItemDto) {
		if(null == gradeItemDto) {
			return null;
		}
		
		GradeItem gradeItem = new GradeItem();
		gradeItem.setId(gradeItemDto.getId());
		gradeItem.setType(gradeItemDto.getType());
		gradeItem.setObtainedScore(gradeItemDto.getObtainedScore());
		gradeItem.setTotalScore(gradeItemDto.getTotalScore());
		
		Optional<Teacher> optionalTeacher = teacherRepository.findById(gradeItemDto.getTeacherId());
		if(optionalTeacher.isPresent()) {
			gradeItem.setTeacherId(optionalTeacher.get().getId());
		} else {
			throw new TeacherNotFoundException();
		}
		
		Optional<Student> optionalStudent = studentRepository.findById(gradeItemDto.getStudentDto().getId());
		if(optionalStudent.isPresent()) {
			gradeItem.setStudent(optionalStudent.get());
		} else {
			throw new StudentNotFountException();
		}
		
		return gradeItem;
	}
	
	public List<GradeItemDto> gradeItemsToGradeItemDtos(List<GradeItem> gradeItems) {
		if(CollectionUtils.isEmpty(gradeItems)) {
			return Collections.emptyList();
		}
		
		List<GradeItemDto> gradeItemDtos = new ArrayList<>();
		gradeItems.stream().forEach(gradeItem -> gradeItemDtos.add(gradeItemToGradeItemDto(gradeItem)));
		return gradeItemDtos;
	}
	
	public GradeItemDto gradeItemToGradeItemDto(GradeItem gradeItem) {
		if(null == gradeItem) {
			return null;
		}
		
		GradeItemDto gradeItemDto = new GradeItemDto();
		gradeItemDto.setId(gradeItem.getId());
		gradeItemDto.setType(gradeItem.getType());
		gradeItemDto.setObtainedScore(gradeItem.getObtainedScore());
		gradeItemDto.setTotalScore(gradeItem.getTotalScore());
		gradeItemDto.setTeacherId(gradeItem.getTeacherId());
		gradeItemDto.setStudentDto(studentMapper.studentToStudentDto(gradeItem.getStudent(), false));
		return gradeItemDto;
	}
}

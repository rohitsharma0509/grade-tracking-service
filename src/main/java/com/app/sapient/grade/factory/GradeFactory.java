package com.app.sapient.grade.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.service.TeacherService;
import com.app.sapient.grade.type.GradeType;

@Component
public class GradeFactory {
	
	private TeacherService teacherService;
	
	@Autowired
	public GradeFactory(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public GradeStrategy getGradeStrategy(GradeItemDto gradeItemDto, Double oldGrade) {
		TeacherDto teacherDto = teacherService.getTeacherById(gradeItemDto.getTeacherId());
		if(GradeType.ASSIGNMENT.toString().equals(gradeItemDto.getType())) {
			return new Assignment(gradeItemDto, oldGrade, teacherDto.getAssignmentPercent());
		} else if(GradeType.EXAM.toString().equals(gradeItemDto.getType())) {
			return new Exam(gradeItemDto, oldGrade, teacherDto.getExamPercent());
		} else if(GradeType.EXTRA_CREDIT.toString().equals(gradeItemDto.getType())) {
			return new ExtraCredit(gradeItemDto, oldGrade, teacherDto.getExtraCreditPercent());
		}
		return null;
	}

}

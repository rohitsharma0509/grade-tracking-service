package com.app.sapient.grade.mapper;

import org.springframework.stereotype.Component;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.model.Teacher;

@Component
public class TeacherMapper {
	public TeacherDto teacherToTeacherDto(Teacher teacher) {
		if(null == teacher) {
			return null;
		}
		
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setAssignmentPercent(teacher.getAssignmentPercent());
		teacherDto.setExamPercent(teacher.getExamPercent());
		teacherDto.setExtraCreditPercent(teacher.getExtraCreditPercent());
		return teacherDto;
	}

	public Teacher teacherDtoToTeacher(TeacherDto teacherDto) {
		if(null == teacherDto) {
			return null;
		}
		
		Teacher teacher = new Teacher();
		teacher.setId(teacherDto.getId());
		teacher.setAssignmentPercent(teacherDto.getAssignmentPercent());
		teacher.setExamPercent(teacherDto.getExamPercent());
		teacher.setExtraCreditPercent(teacherDto.getExtraCreditPercent());
		return teacher;
	}
}

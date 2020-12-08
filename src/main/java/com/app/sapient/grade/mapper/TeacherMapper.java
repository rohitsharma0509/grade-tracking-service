package com.app.sapient.grade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.model.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	@Mappings({ 
		@Mapping(source = "teacherDto.assignmentPercent", target = "assignmentPercent"),
		@Mapping(source = "teacherDto.examPercent", target = "examPercent"),
		@Mapping(source = "teacherDto.extraCreditPercent", target = "extraCreditPercent")
	})
	Teacher toTeacher(TeacherDto teacherDto);

	TeacherDto toTeacherDto(Teacher teacher);

}

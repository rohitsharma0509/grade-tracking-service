package com.app.sapient.grade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.model.Student;

@Mapper(componentModel = "spring", uses = { GradeItemMapper.class })
public interface StudentMapper {
	@Mappings({ 
		@Mapping(source = "student.gradeItems", target = "gradeItemDtos")
	})
	StudentDto toStudentDto(Student student);

	Student toStudent(StudentDto studentDto);
}

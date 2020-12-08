package com.app.sapient.grade.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.model.GradeItem;

@Mapper(componentModel = "spring", uses = { StudentMapper.class })
public interface GradeItemMapper {

	List<GradeItemDto> toGradeItemDtos(List<GradeItem> gradeItems);

	//Mapping fields with different name
	@Mappings({ 
		@Mapping(source = "gradeItem.teacherId", target = "teacherId"),
		@Mapping(source = "gradeItem.student.id", target = "studentId") 
	})
	GradeItemDto toGradeItemDto(GradeItem gradeItem);

	List<GradeItem> toGradeItems(List<GradeItemDto> gradeItemDtos);

	//Mapping from Multiple sources
	@Mappings({ 
		@Mapping(source = "gradeItemDto.id", target = "id"),
		@Mapping(source = "gradeItemDto.teacherId", target = "teacherId"),
		@Mapping(source = "studentDto", target = "student")
	})
	GradeItem toGradeItem(GradeItemDto gradeItemDto, StudentDto studentDto);

}

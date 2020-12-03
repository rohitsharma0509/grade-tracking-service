package com.app.sapient.grade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class StudentDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("gradeItemDtos")
	private List<GradeItemDto> gradeItemDtos;
	
	@JsonProperty("gradeAsPercent")
	private Double gradeAsPercent;

}

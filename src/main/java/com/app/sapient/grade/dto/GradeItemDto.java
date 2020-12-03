package com.app.sapient.grade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class GradeItemDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("obtainedScore")
	private Double obtainedScore;
	
	@JsonProperty("totalScore")
	private Double totalScore;
	
	@JsonProperty("teacherId")
	private Long teacherId;
	
	@JsonProperty("studentDto")
	private StudentDto studentDto;

}

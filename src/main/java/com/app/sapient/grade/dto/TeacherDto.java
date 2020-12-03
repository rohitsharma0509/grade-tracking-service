package com.app.sapient.grade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class TeacherDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("assignmentPercent")
	private Double assignmentPercent;
	
	@JsonProperty("examPercent")
	private Double examPercent;
	
	@JsonProperty("extraCreditPercent")
	private Double extraCreditPercent;
	
}

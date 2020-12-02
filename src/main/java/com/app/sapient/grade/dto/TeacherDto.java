package com.app.sapient.grade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAssignmentPercent() {
		return assignmentPercent;
	}
	public void setAssignmentPercent(Double assignmentPercent) {
		this.assignmentPercent = assignmentPercent;
	}
	public Double getExamPercent() {
		return examPercent;
	}
	public void setExamPercent(Double examPercent) {
		this.examPercent = examPercent;
	}
	public Double getExtraCreditPercent() {
		return extraCreditPercent;
	}
	public void setExtraCreditPercent(Double extraCreditPercent) {
		this.extraCreditPercent = extraCreditPercent;
	}
}

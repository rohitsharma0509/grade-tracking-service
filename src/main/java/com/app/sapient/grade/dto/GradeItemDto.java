package com.app.sapient.grade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getObtainedScore() {
		return obtainedScore;
	}

	public void setObtainedScore(Double obtainedScore) {
		this.obtainedScore = obtainedScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public StudentDto getStudentDto() {
		return studentDto;
	}

	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}
}

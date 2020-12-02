package com.app.sapient.grade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GradeItemDto> getGradeItemDtos() {
		return gradeItemDtos;
	}

	public void setGradeItemDtos(List<GradeItemDto> gradeItemDtos) {
		this.gradeItemDtos = gradeItemDtos;
	}

	public Double getGradeAsPercent() {
		return gradeAsPercent;
	}

	public void setGradeAsPercent(Double gradeAsPercent) {
		this.gradeAsPercent = gradeAsPercent;
	}
}

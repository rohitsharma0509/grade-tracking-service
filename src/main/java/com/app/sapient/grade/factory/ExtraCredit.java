package com.app.sapient.grade.factory;

import com.app.sapient.grade.dto.GradeItemDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExtraCredit implements GradeStrategy {
	
	private GradeItemDto gradeItemDto;
	private Double oldGrade;
	private Double weight;
	
	@Override
	public Double getGradeAsPercentage() {
		return  (oldGrade + (weight/100));
	}
}

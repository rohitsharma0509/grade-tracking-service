package com.app.sapient.grade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
	private Long id;
    
	@Column(name = "assignment")
	private Double assignmentPercent;
	
	@Column(name = "exam")
	private Double examPercent;
	
	@Column(name = "extra_credit")
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
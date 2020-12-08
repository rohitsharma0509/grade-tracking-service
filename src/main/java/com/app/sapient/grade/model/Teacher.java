package com.app.sapient.grade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@Getter
@Setter
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

}
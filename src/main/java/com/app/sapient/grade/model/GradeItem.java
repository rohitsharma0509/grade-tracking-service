package com.app.sapient.grade.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grade_items")
public class GradeItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grade_item_id")
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "obtained_score")
	private Double obtainedScore;

	@Column(name = "total_score")
	private Double totalScore;

	@Column(name = "teacher_id")
	private Long teacherId;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "student_id")
	private Student student;

}

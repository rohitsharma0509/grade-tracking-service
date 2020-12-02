package com.app.sapient.grade.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<GradeItem> gradeItems;

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

	public List<GradeItem> getGradeItems() {
		return gradeItems;
	}

	public void setGradeItems(List<GradeItem> gradeItems) {
		this.gradeItems = gradeItems;
	}
}

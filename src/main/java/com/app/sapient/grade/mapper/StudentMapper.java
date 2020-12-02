package com.app.sapient.grade.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.model.Student;

@Component
public class StudentMapper {
	
	@Autowired
	private GradeItemMapper gradeItemMapper;

	public Student studentDtoToStudent(StudentDto studentDto) {
		if(null == studentDto) {
			return null;
		}
		
		Student student = new Student();
		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		return student;
	}
	
	public StudentDto studentToStudentDto(Student student) {
		if(null == student) {
			return null;
		}
		
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setGradeItemDtos(gradeItemMapper.gradeItemsToGradeItemDtos(student.getGradeItems()));
		//studentDto.setGradeAsPercent(gradeStrategy.getGradeAsPercentage());
		return studentDto;
	}
}

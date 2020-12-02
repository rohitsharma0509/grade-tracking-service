package com.app.sapient.grade.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.model.GradeItem;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.service.TeacherService;
import com.app.sapient.grade.type.GradeType;

@Component
public class StudentMapper {
	
	@Autowired
	private GradeItemMapper gradeItemMapper;
	
	@Autowired
	private TeacherService teacherService;

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
		return studentToStudentDto(student, true);
	}
	
	public StudentDto studentToStudentDto(Student student, boolean isGradeItemRequired) {
		if(null == student) {
			return null;
		}
		
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		if(isGradeItemRequired) {
			studentDto.setGradeItemDtos(gradeItemMapper.gradeItemsToGradeItemDtos(student.getGradeItems(), false));
		}
		if(!CollectionUtils.isEmpty(student.getGradeItems())) {
			studentDto.setGradeAsPercent(getFinalGrade(student));
		}
		
		return studentDto;
	}
	
	private Double getFinalGrade(Student student) {
		Double obtainedAssignmentScore = 0.0, totalAssignmentScore = 0.0, obtainedExamScore = 0.0, totalExamScore = 0.0, finalGrade = 0.0;
		int noOfExtraCredit = 0;
		Long teacherId = 0L;
		
		for(GradeItem gradeItem : student.getGradeItems()) {
			teacherId = gradeItem.getTeacherId();
			if(GradeType.ASSIGNMENT.toString().equals(gradeItem.getType())) {
				obtainedAssignmentScore += gradeItem.getObtainedScore();
				totalAssignmentScore += gradeItem.getTotalScore();
			} else if(GradeType.EXAM.toString().equals(gradeItem.getType())) {
				obtainedExamScore += gradeItem.getObtainedScore();
				totalExamScore += gradeItem.getTotalScore();
			} else if(GradeType.EXTRA_CREDIT.toString().equals(gradeItem.getType())) {
				noOfExtraCredit++;
			}
		}
		
		TeacherDto teacherDto = teacherService.getTeacherById(teacherId);
		
		if(obtainedAssignmentScore > 0.0) {
			finalGrade = (obtainedAssignmentScore/totalAssignmentScore)*(teacherDto.getAssignmentPercent()/100);
		}
		
		if(noOfExtraCredit > 0) {
			finalGrade = finalGrade + ((noOfExtraCredit*teacherDto.getExtraCreditPercent())/100);
		}
		
		if(obtainedExamScore>0.0) {
			finalGrade = finalGrade + ((obtainedExamScore/totalExamScore)*(teacherDto.getExamPercent())/100);
		}
		finalGrade = finalGrade * 100;
		return finalGrade;
	}
}

package com.app.sapient.grade.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.factory.GradeFactory;
import com.app.sapient.grade.factory.GradeStrategy;
import com.app.sapient.grade.mapper.StudentMapper;
import com.app.sapient.grade.model.Student;
import com.app.sapient.grade.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private StudentMapper studentMapper;
	private GradeFactory gradeFactory;

	@Autowired
	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, GradeFactory gradeFactory) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
		this.gradeFactory = gradeFactory;
	}

	@Transactional
	public StudentDto addStudent(StudentDto studentDto) {
		return studentMapper.toStudentDto(studentRepository.save(studentMapper.toStudent(studentDto)));
	}

	public StudentDto getStudentById(Long studentId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		if (optionalStudent.isPresent()) {
			StudentDto studentDto = studentMapper.toStudentDto(optionalStudent.get());
			if(!CollectionUtils.isEmpty(studentDto.getGradeItemDtos())) {
				Double gradeAsPercent = 0.0;
				for(GradeItemDto gradeItemDto : studentDto.getGradeItemDtos()) {
					GradeStrategy gradeStrategy = gradeFactory.getGradeStrategy(gradeItemDto, gradeAsPercent);
					gradeAsPercent = gradeStrategy.getGradeAsPercentage();
				}
				BigDecimal bd = BigDecimal.valueOf(gradeAsPercent*100);
				studentDto.setGradeAsPercent(bd.setScale(2, RoundingMode.FLOOR).doubleValue());
			}
			return studentDto;
		} else {
			throw new StudentNotFountException();
		}
	}

}

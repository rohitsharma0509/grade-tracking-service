package com.app.sapient.grade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.mapper.TeacherMapper;
import com.app.sapient.grade.model.Teacher;
import com.app.sapient.grade.repository.TeacherRepository;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;
	private TeacherMapper teacherMapper;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
		this.teacherRepository = teacherRepository;
		this.teacherMapper = teacherMapper;
	}

	@Transactional
	public TeacherDto configureTeacher(TeacherDto teacherDto) {
		return teacherMapper.toTeacherDto(teacherRepository.save(teacherMapper.toTeacher(teacherDto)));
	}

	public TeacherDto getTeacherById(Long teacherId) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			return teacherMapper.toTeacherDto(optionalTeacher.get());
		} else {
			throw new TeacherNotFoundException();
		}
	}

	@Transactional
	public void deleteTeacherById(Long teacherId) {
		teacherRepository.deleteById(teacherId);
	}
}

package com.app.sapient.grade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.sapient.grade.dto.TeacherDto;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping(value = "/teachers")
	public ResponseEntity<TeacherDto> configureTeacher(@RequestBody TeacherDto teacherDto) {
		try {
			TeacherDto configuredTeacherDto = teacherService.configureTeacher(teacherDto);
			return new ResponseEntity<>(configuredTeacherDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/teachers/{id}")
	public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long teacherId) {
		try {
			TeacherDto configuredTeacherDto = teacherService.getTeacherById(teacherId);
			return new ResponseEntity<>(configuredTeacherDto, HttpStatus.OK);
		} catch(TeacherNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

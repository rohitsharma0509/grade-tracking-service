package com.app.sapient.grade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.sapient.grade.dto.StudentDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.service.StudentService;

@RestController
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

		@Autowired
		private StudentService studentService;

		@PostMapping(value = "/students")
		public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
			try {
				StudentDto createdStudent = studentService.addStudent(studentDto);
				return new ResponseEntity<>(createdStudent, HttpStatus.OK);
			} catch (Exception e) {
				LOGGER.error("Exception while adding student: ", e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@GetMapping("/students/{id}")
		public ResponseEntity<StudentDto> getStudentDetails(@PathVariable("id") Long studentId) {
			try {
				StudentDto studentDto = studentService.getStudentById(studentId);
				return new ResponseEntity<>(studentDto, HttpStatus.OK);
			} catch(StudentNotFountException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} catch(Exception e) {
				LOGGER.error("Exception while getting student details: ", e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

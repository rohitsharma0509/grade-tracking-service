package com.app.sapient.grade.controller;

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

		@Autowired
		private StudentService studentService;

		@PostMapping(value = "/students")
		public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
			try {
				StudentDto createdStudent = studentService.addStudent(studentDto);
				return new ResponseEntity<StudentDto>(createdStudent, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<StudentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@GetMapping("/students/{id}")
		public ResponseEntity<StudentDto> getStudentDetails(@PathVariable("id") Long studentId) {
			try {
				StudentDto studentDto = studentService.getStudentById(studentId);
				return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
			} catch(StudentNotFountException e) {
				return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<StudentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

package com.app.sapient.grade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.sapient.grade.dto.GradeItemDto;
import com.app.sapient.grade.exception.StudentNotFountException;
import com.app.sapient.grade.exception.TeacherNotFoundException;
import com.app.sapient.grade.service.GradeService;

@RestController
public class GradeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

	@Autowired
	private GradeService gradeService;
	
	@PostMapping(value = "/grades")
	public ResponseEntity<GradeItemDto> addGradeItem(@RequestBody GradeItemDto gradeItemDto) {
		if(null == gradeItemDto || null == gradeItemDto.getStudentDto() || null == gradeItemDto.getStudentDto().getId() || null == gradeItemDto.getTeacherId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			GradeItemDto createdItem = gradeService.addGradeItem(gradeItemDto);
			return new ResponseEntity<>(createdItem, HttpStatus.OK);
		} catch(TeacherNotFoundException | StudentNotFountException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			LOGGER.error("Exception while adding grade item: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

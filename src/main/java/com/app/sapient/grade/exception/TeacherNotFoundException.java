package com.app.sapient.grade.exception;

public class TeacherNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TeacherNotFoundException() {}
	
	public String getMessage() {
		return message;
	}
}

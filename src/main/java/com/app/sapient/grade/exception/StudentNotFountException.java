package com.app.sapient.grade.exception;

public class StudentNotFountException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public StudentNotFountException() {}
	
	public String getMessage() {
		return message;
	}
}

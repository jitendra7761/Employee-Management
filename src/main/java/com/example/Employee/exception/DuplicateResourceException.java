package com.example.Employee.exception;

import com.example.Employee.payload.EmployeeDto;

public class DuplicateResourceException extends Exception {
	public DuplicateResourceException(String string, String string1, EmployeeDto employeeDto) {
		// TODO Auto-generated constructor stub
		super(string);
	}
}

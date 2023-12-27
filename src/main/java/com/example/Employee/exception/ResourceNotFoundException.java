package com.example.Employee.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Employee.payload.EmployeeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{



	
	 private static final long serialVersionUID = 1L;

	    public ResourceNotFoundException(String message){
	        super(message);
	    }

		public ResourceNotFoundException(String string, String string2, Long employeeId) {
			super(string);
		}

		
}

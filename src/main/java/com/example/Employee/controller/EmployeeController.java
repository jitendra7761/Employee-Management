package com.example.Employee.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.config.AppConstants;
import com.example.Employee.exception.DuplicateResourceException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.model.Employee;
import com.example.Employee.payload.EmployeeDto;
import com.example.Employee.payload.PostResponse;
import com.example.Employee.service.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	  // @Autowired
	   // private Logger logger;
	
	//InsertEmployee
	@PostMapping("/employeeCreate")
	public ResponseEntity<EmployeeDto> createAllEmployee(@RequestBody EmployeeDto employeeDto) throws DuplicateResourceException {
		
	EmployeeDto employeeSAVE = employeeServiceImpl.createEmployee(employeeDto);
	
		return new ResponseEntity(employeeSAVE,HttpStatus.OK);
	}

	
	//FindAllEmployee
	@GetMapping("/getEmployee")
	
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		
		List<EmployeeDto> emplo=employeeServiceImpl.getAllEmployee();
		logger.info("This info message---------"+emplo);
		return new ResponseEntity(emplo,HttpStatus.OK);
		
	
	}
	
	
	
	//FindSingleEmp
@GetMapping("/employee/{id}")
	
	public ResponseEntity<EmployeeDto> getAllEmployeeById(@PathVariable(value="id") Long empId)
	       
 throws ResourceNotFoundException{
		
	EmployeeDto emp=employeeServiceImpl.getSigleEmp(empId);
			System.out.println("empController----------------"+emp);
			logger.info("This info message---------"+emp);
		return new  ResponseEntity<EmployeeDto>(emp,HttpStatus.OK);
	
	}
	
	
	//FindEmployeeById
@GetMapping("/employees/{id}")
	
	public ResponseEntity<PostResponse> getAllEmployeeById(@PathVariable(value="id") Long employeeId, @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
	       @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR) String sortDir) 
 throws ResourceNotFoundException{
		
	PostResponse emp=employeeServiceImpl.getEmployeeById(employeeId,pageNumber,pageSize,sortBy,sortDir);
			System.out.println("emp----------------"+emp.getPageSize());
		
		return new  ResponseEntity<PostResponse>(emp,HttpStatus.OK);
	
	}


//search
	@GetMapping("/search/{firwstName}")
	public ResponseEntity<List<EmployeeDto>> searchEmployeeByName(@PathVariable("firwstName") String name) throws ResourceNotFoundException {
		List<EmployeeDto> result =employeeServiceImpl.searchEmployee(name);
		
		
		return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
	}
	
	
	
	//Updating the Employee

	@PutMapping("/{userId}")
	public ResponseEntity<EmployeeDto> updateUser( @RequestBody EmployeeDto userDto, @PathVariable("userId") Long employeeId) {
		EmployeeDto updatedUser = employeeServiceImpl.update(userDto, employeeId);
		return ResponseEntity.ok(updatedUser);
	}
	
	  //Delete employee by Id
	
	@DeleteMapping("/employees/{userId}")
	public String deleteUser(@PathVariable("userId") Long employeeId) throws ResourceNotFoundException{
		employeeServiceImpl.deleteEmployee(employeeId);
	return ("Employee deleted from Table");	
	}

    //pagination 
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
	        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
	       @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR) String sortDir) {

		PostResponse postResponse =  employeeServiceImpl.getAllPost(pageNumber, pageSize, sortBy, sortDir);
	    
	    System.out.println("postResponse--------------"+postResponse);
	    return new ResponseEntity(postResponse,HttpStatus.ACCEPTED);
	}
	
	
	
	//FindAllEmployee
		@GetMapping("/getEmployeeSorting")
		
		public ResponseEntity<List<Employee>> getAllEmployeesSortedByName() throws ResourceNotFoundException{
			
			List<Employee> emplo=employeeServiceImpl.getAllEmployeesSortedByName();
			logger.info("This info message---------"+emplo);
			return new ResponseEntity(emplo,HttpStatus.OK);
			
		
		}
}

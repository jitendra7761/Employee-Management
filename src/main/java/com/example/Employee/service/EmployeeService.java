package com.example.Employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Employee.exception.DuplicateResourceException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.model.Employee;
import com.example.Employee.payload.EmployeeDto;
import com.example.Employee.payload.PostResponse;

public interface EmployeeService {
	
	
	 List<Employee> getAllEmployeesSortedByName()throws ResourceNotFoundException;
	     
	
	

	//Insert Employee in Table
	EmployeeDto createEmployee(EmployeeDto employeeDto) throws DuplicateResourceException;
	
	
	//fatch ALL Employee in the Table
	List<EmployeeDto> getAllEmployee();
	
	//findEmployeebyid
	EmployeeDto getSigleEmp(Long empId)throws ResourceNotFoundException;
	
	PostResponse getEmployeeById(Long employeeId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) throws ResourceNotFoundException;
	
	//search Employee
	List<EmployeeDto> searchEmployee(String name) throws ResourceNotFoundException;
			
	//Update Employee
		
	EmployeeDto update(EmployeeDto emp,Long employeeId);
	
	//Delete Employee
	
     void deleteEmployee(Long employeeId)throws ResourceNotFoundException;
	
       //Pagination
    // Page<EmployeeDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
     

 	//get all posts
 	
 	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
 	
}

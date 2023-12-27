package com.example.Employee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import com.example.Employee.Repository.EmployeeRepo;
import com.example.Employee.exception.DuplicateResourceException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.model.Employee;
import com.example.Employee.payload.EmployeeDto;
import com.example.Employee.payload.PostResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	
	private EmployeeRepo employeeRepo;
	
	
	@Autowired 
	private ModelMapper modelMapper;
	
	
	
	/*@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee=modelMapper.map(employeeDto, Employee.class);
		
		Employee savedEmployee=employeeRepo.save(employee);
		
		EmployeeDto SaveEDto=modelMapper.map(savedEmployee, EmployeeDto.class);
		
		return SaveEDto;
	}*/
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) throws DuplicateResourceException {
	    Employee employee = modelMapper.map(employeeDto, Employee.class);

	    // Check if an employee with the same details already exists
	    Optional<Employee> existingEmployee = employeeRepo.findByFirstNameAndLast_Name(employee.getFirstName(), employee.getLast_Name());

	    if (existingEmployee.isPresent()) {
	        throw new DuplicateResourceException("Employee already exists with the same details", "employeeDto", employeeDto);
	    }

	    Employee savedEmployee = employeeRepo.save(employee);
	    EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

	    return savedEmployeeDto;
	}



	
	
	
	


	@Override
	public List<EmployeeDto> getAllEmployee() {
	List<Employee> emp =employeeRepo.findAll(); 
	logger.debug("This debug message----------"+emp);
	logger.info("This info message---------"+emp);
	logger.warn("This  warning-----------"+emp);
	logger.error("This error-------"+emp );
	List<EmployeeDto> rt=emp.stream().map((user) -> modelMapper.map(user,EmployeeDto.class)).collect(Collectors.toList());
	return rt;
	
	}


	@Override
	public PostResponse getEmployeeById(Long employeeId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) throws ResourceNotFoundException {
	    Employee employee = this.employeeRepo.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee ID does not exist in the table", "employeeId", employeeId));

	    Sort sort = Sort.by(sortBy);
	    if (sortDir.equalsIgnoreCase("desc")) {
	        sort = sort.descending();
	    } else {
	        sort = sort.ascending();
	    }

	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    Page<Employee> employeePage = this.employeeRepo.findAll(pageable);
	    List<Employee> employeeList = employeePage.getContent();

	    List<EmployeeDto> employeeDtos = employeeList.stream()
	            .map(emp -> this.modelMapper.map(emp, EmployeeDto.class))
	            .collect(Collectors.toList());

	    PostResponse employeeDto = new PostResponse();
	    employeeDto.setContent(employeeDtos);
	    employeeDto.setPageNumber(employeePage.getNumber());
	    employeeDto.setPageSize(employeePage.getSize());
	    employeeDto.setTotalElements(employeePage.getTotalElements());
	    employeeDto.setTotalPages(employeePage.getTotalPages());
	    employeeDto.setLastPage(employeePage.isLast());

	    return employeeDto;
	}


	
	


	//SearchByName
	

	@Override
	public List<EmployeeDto> searchEmployee(String name) throws ResourceNotFoundException {
	    List<Employee> employees = employeeRepo.searchByName("%" + name + "%");
	    
	    if (employees.isEmpty()) {
	        throw new ResourceNotFoundException("Employee name does not exist in the table");
	    }
	    
	    return employees.stream()
	            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
	            .collect(Collectors.toList());
	}

	@Override
	public EmployeeDto update(EmployeeDto emp,Long employeeId) {
		Employee empl=employeeRepo.findById(emp.getId()).get();
		empl.setFirstName(emp.getFirstName());
		empl.setLast_Name(emp.getLast_Name());
		empl.setAge(emp.getAge());
	Employee savedEmp=employeeRepo.save(empl);
	EmployeeDto SaveEDto=modelMapper.map(savedEmp, EmployeeDto.class);
		return SaveEDto;
	}

	@Override
	public void deleteEmployee(Long employeeId) throws ResourceNotFoundException {
	    Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);

	    if (employeeOptional.isEmpty()) {
	        throw new ResourceNotFoundException("Employee ID does not exist in the table");
	    }

	    employeeRepo.deleteById(employeeId);
	}









/*	@Override

public Page<EmployeeDto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();

    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    Employee em=modelMapper.map(pageable, Employee.class);

    return employeeRepo.findAll(em, pageable);
}*/


	 @Override
	    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

	        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

	        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

	        Page<Employee> pagePost = this.employeeRepo.findAll(p);

	        List<Employee> allPosts = pagePost.getContent();

	        List<EmployeeDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, EmployeeDto.class))
	                .collect(Collectors.toList());

	        PostResponse postResponse = new PostResponse();

	        postResponse.setContent(postDtos);
	        postResponse.setPageNumber(pagePost.getNumber());
	        postResponse.setPageSize(pagePost.getSize());
	        postResponse.setTotalElements(pagePost.getTotalElements());

	        postResponse.setTotalPages(pagePost.getTotalPages());
	        postResponse.setLastPage(pagePost.isLast());

	        return postResponse;
	    }



	@Override
	public EmployeeDto getSigleEmp(Long empId) throws ResourceNotFoundException {

		Employee em	=employeeRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exits", "employeeId", empId));
		System.out.println("emRepo----------------"+em);
		EmployeeDto empdto=modelMapper.map(em,EmployeeDto.class);
		System.out.println("empdtoService----------------"+empdto);
	return empdto;
	}









	@Override
	public List<Employee> getAllEmployeesSortedByName() throws ResourceNotFoundException {
		
		return employeeRepo.findAllEmployeesSortedByName();
	}









	
	}


	

    


	



	



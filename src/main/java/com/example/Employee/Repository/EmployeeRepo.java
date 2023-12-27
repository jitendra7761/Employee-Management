package com.example.Employee.Repository;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.Employee.model.Employee;
import com.example.Employee.payload.EmployeeDto;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	
	  @Query("SELECT e FROM Employee e ORDER BY e.firstName ")
	    List<Employee> findAllEmployeesSortedByName();

	@Query("select p from Employee p where p.firstName like :n")
	List<Employee> searchByName(@Param("n") String name);

	//Optional<Employee> getName(String firstName, String last_Name);
	 //Optional<Employee> findByFirstNameAndLast_Name (String firstName, String last_Name);
	
	//@Query("select p from Employee p where p.firstName AND P.last_Name")
		   // Optional<Employee> findByFirstNameAndLast_Name(String firstName, String last_Name);
	
	@Query("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.last_Name = :last_Name")
	Optional<Employee> findByFirstNameAndLast_Name(@Param("firstName") String firstName, @Param("last_Name") String last_Name);
                        
		}



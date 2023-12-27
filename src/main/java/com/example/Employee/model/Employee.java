package com.example.Employee.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Generated;


@Entity
@Table(name="employee")

public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String last_Name;
	private int age;

	
	public Employee() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLast_Name() {
		return last_Name;
	}


	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", last_Name=" + last_Name + ", age=" + age + "]";
	}


	public Employee(long id, String firstName, String last_Name, int age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.last_Name = last_Name;
		this.age = age;
	}
	
	
}

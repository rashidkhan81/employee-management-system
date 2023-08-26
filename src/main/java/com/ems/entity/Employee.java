package com.ems.entity;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.processing.Generated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private int age;
	private String gender;
	private LocalDate dob;
	private String email;
	private String password;
	private String address;
	private long phone;
	private String role;
	private long salary;
	
	@ManyToOne
	@JoinColumn(name = "Department_Id")
	private Department dept;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<TimeandAttendance> timeAndAttendanceList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<TimeandAttendance> getTimeAndAttendanceList() {
		return timeAndAttendanceList;
	}

	public void setTimeAndAttendanceList(List<TimeandAttendance> timeAndAttendanceList) {
		this.timeAndAttendanceList = timeAndAttendanceList;
	}
    
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	public Employee(int id, String name, int age, String gender, LocalDate dob, String email, String password,
			String address, long phone, String role, long salary, Department dept,
			List<TimeandAttendance> timeAndAttendanceList) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.salary = salary;
		this.dept = dept;
		this.timeAndAttendanceList = timeAndAttendanceList;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


  
	
}
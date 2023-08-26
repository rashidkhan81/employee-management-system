package com.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.entity.Department;

public interface DeptService {

	public void saveDepartment(Department dept);
	public List<Department> getAllDepartment();
	public Department getDeptartment(int id);
}

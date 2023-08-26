package com.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.DepartmentRepository;
import com.ems.entity.Department;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DepartmentRepository deptrepo;
	
	@Override
	public void saveDepartment(Department dept) {
		deptrepo.save(dept);
		
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> l = deptrepo.findAll();
		return l;
		
	}

	@Override
	public Department getDeptartment(int id) {
	  Optional<Department> op = deptrepo.findById(id); 
		return op.get();
	}


}

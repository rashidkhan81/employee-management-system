package com.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.dao.EmployeeRepository;
import com.ems.entity.Department;
import com.ems.entity.Employee;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmployeeRepository emprepo;


	
	@Override
	public Employee getEmployee(int id) {
		Optional<Employee> e = emprepo.findById(id); 
		if(e.isPresent()==true) {
			return e.get();
		}
			return null;
		
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> l = emprepo.findAll();
		return l;
	}

	@Override
	public void delete(int id) {
		emprepo.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		emprepo.deleteAll();
	}



	@Override
	public void saveEmployee(Employee emp) {
	    emprepo.save(emp);
		
	}

	@Override
	public Employee getbyEmail(String email) {
		Employee e = emprepo.getEmployeeByUserName(email);
		return e;
	}




	

	
}

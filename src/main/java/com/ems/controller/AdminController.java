package com.ems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.TimeandAttendance;
import com.ems.service.AttendanceService;
import com.ems.service.DeptService;
import com.ems.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AttendanceService ats;
	
	@Autowired
	private EmpService empservice;
	
	@Autowired
	private DeptService deptservice;
    
	@Autowired
	private AttendanceService attenserv;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/addEmp")
	public String signup(Model model) {
	    model.addAttribute("departments", deptservice.getAllDepartment());
	    return "addEmp";
	}

	@PostMapping("/process")
	public String process(@ModelAttribute("emp") Employee emp, Model model) {
		String email = emp.getEmail();
		Employee e = empservice.getbyEmail(email);
		if(e!=null) {
			model.addAttribute("errorMessage", "User with this email already exists");
			return "addEmp";
		}
		Department dept = emp.getDept();
	    Department dept2 = deptservice.getDeptartment(dept.getDeptid()); // retrieve managed Department entity
	    emp.setDept(dept2); // set managed Department entity to Employee entity
	    emp.setPassword(passwordEncoder.encode(emp.getPassword()));
	    empservice.saveEmployee(emp);
	    return "home";
	}

	@GetMapping("/empall")
	public String viewall(Model model) {
		List<Employee> l = empservice.getAllEmployee();
		model.addAttribute("employees",l);
		return "empall";
	}
	@GetMapping("/addDept")
	public String addDept() {
		return "addDept";
	}
	@PostMapping("/processdept")
	public String processdept(@ModelAttribute("dept") Department dept) {
		deptservice.saveDepartment(dept);
		return "home";
	}
	@GetMapping("/deptlist")
	public String deptlist(Model model) {
		List<Department> l = deptservice.getAllDepartment();
		model.addAttribute("departments",l);
		return "deptlist";
	}
	@GetMapping("/editemp/{id}")
	public String editEmployee(@PathVariable("id") int id, Model model) {
		Employee e = empservice.getEmployee(id);
		model.addAttribute("employee",e);
		return "editemp";
	}
	@PostMapping("/editprocess")
	public String editprocess(@ModelAttribute("emp") Employee emp,@RequestParam("deptid") int deptid) {
		// retrieve existing employee entity from the database
	    Employee existingEmp = empservice.getEmployee(emp.getId());
	    if (existingEmp == null) {
	        // handle error when employee not found
	        return "error";
	    }
	    // update fields from the submitted form
	    existingEmp.setName(emp.getName());
	    existingEmp.setEmail(emp.getEmail());
	    existingEmp.setSalary(emp.getSalary());
	    existingEmp.setId(emp.getId());
	    existingEmp.setAddress(emp.getAddress());
	    existingEmp.setAge(emp.getAge());
	    existingEmp.setPhone(emp.getPhone());
	    existingEmp.setDob(emp.getDob());
	    existingEmp.setRole(emp.getRole()); 
	    
	    
	    
	    // set managed department entity to employee entity
	    Department dept = deptservice.getDeptartment(deptid);
	    existingEmp.setDept(dept);
	    // save the updated entity back to the database
	    empservice.saveEmployee(existingEmp);
		return "home";
	}
	@GetMapping("/attendances")
	public String allattendances(Model model){
		List<TimeandAttendance> l = attenserv.getAllattendances();
		model.addAttribute("attendances",l);
		return "allattendances";
	}
	@GetMapping("/employees")
	public String employees() {
		return "employees";
	}
	@GetMapping("/deleteemp")
	public String deleteemp() {
		return "deleteemp";
	}
	@PostMapping("/delemp")
	public String deleteemployee(@RequestParam("eid") int id,Model model) {
		if(empservice.getEmployee(id)!=null) {
		empservice.delete(id);
		model.addAttribute("successMessage","Employee Successfully deleted");
		return "deleteemp";
		}
		else {
			model.addAttribute("errorMessage","Employee with this ID is not present");
			return "deleteemp";
		}
	}
	
	
    
	
	
	
	
	
	
	/*
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		return empservice.getEmployee(id);
	}
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		return empservice.getAllEmployee();
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmp(@PathVariable("id") int id) {
		empservice.delete(id);
		return "Employee with id "+id+" has been successfully deleted";
	}
	@DeleteMapping("/AllEmployees")
	public String deleteall() {
		empservice.deleteAll();
		return "All the employees have deleted successfully";
	}
	@GetMapping("/employeebyemail/{email}")
	public List<Employee> getByEmail(@PathVariable("email") String email) { 
		List<Employee> l = empservice.getbyEmail(email);
		return l;
	}
	*/
}

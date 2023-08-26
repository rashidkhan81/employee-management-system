package com.ems.service;

import java.util.List;

import com.ems.entity.Department;
import com.ems.entity.Employee;


public interface EmpService {
  public void saveEmployee(Employee emp);
  public Employee getEmployee(int id);
  public List<Employee> getAllEmployee();
  public void delete(int id);
  public void deleteAll();
  public Employee getbyEmail(String email);
}

package com.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
  
}

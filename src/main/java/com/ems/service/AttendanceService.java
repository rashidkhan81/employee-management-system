package com.ems.service;

import java.time.LocalDate;
import java.util.List;

import com.ems.entity.Employee;
import com.ems.entity.TimeandAttendance;

public interface AttendanceService {

	public void saveAttendance(TimeandAttendance at);
	public List<TimeandAttendance> getAllattendances();
	public List<TimeandAttendance> getempattendance(Employee employee);
	public TimeandAttendance getbydate(LocalDate date,int empid);

}

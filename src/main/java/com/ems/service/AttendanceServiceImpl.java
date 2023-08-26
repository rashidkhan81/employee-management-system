package com.ems.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.AttendanceRepository;
import com.ems.entity.Employee;
import com.ems.entity.TimeandAttendance;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceRepository atrepo;

	@Override
	public void saveAttendance(TimeandAttendance at) {
		atrepo.save(at);
		
	}
	@Override
	public List<TimeandAttendance> getAllattendances(){
		return atrepo.findAll();
	}
	@Override
	public List<TimeandAttendance> getempattendance(Employee employee) {
		List<TimeandAttendance> l = atrepo.findByEmployee(employee);
		return l;
	}
	@Override
	public TimeandAttendance getbydate(LocalDate date, int empid) {
		TimeandAttendance t = atrepo.atbydate(date, empid);
		return t;
	}
	
	
	
	
}

package com.ems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class TimeandAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aid;

	private LocalDate date;
	private LocalTime clockInTime;
	private LocalTime clockOutTime;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(LocalTime clockInTime) {
		this.clockInTime = clockInTime;
	}

	public LocalTime getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(LocalTime clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TimeandAttendance(int aid, LocalDate date, LocalTime clockInTime, LocalTime clockOutTime,
			Employee employee) {
		super();
		this.aid = aid;
		this.date = date;
		this.clockInTime = clockInTime;
		this.clockOutTime = clockOutTime;
		this.employee = employee;
	}

	public TimeandAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TimeandAttendance [aid=" + aid + ", date=" + date + ", clockInTime=" + clockInTime + ", clockOutTime="
				+ clockOutTime + ", employee=" + employee + "]";
	}

	

}

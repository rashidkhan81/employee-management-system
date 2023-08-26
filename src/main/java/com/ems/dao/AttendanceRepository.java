package com.ems.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.entity.Employee;
import com.ems.entity.TimeandAttendance;

@Repository
public interface AttendanceRepository extends JpaRepository<TimeandAttendance, Integer>{

	@Query("select t from TimeandAttendance t where t.date = :date and t.employee.id = :empid")
	public TimeandAttendance atbydate(@Param("date") LocalDate date, @Param("empid") int id );
	
	public List<TimeandAttendance> findByEmployee(Employee employee);
	
}

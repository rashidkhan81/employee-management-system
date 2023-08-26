package com.ems.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.config.CustomUserDetails;
import com.ems.dao.AttendanceRepository;
import com.ems.entity.Employee;
import com.ems.entity.TimeandAttendance;
import com.ems.service.AttendanceService;
import com.ems.service.EmpService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employees")
public class UserController {

	@Autowired
	private EmpService empserivce;

	@Autowired
	private AttendanceService ats;


	@GetMapping("/empHome")
	public String userHome(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		Employee e = empserivce.getbyEmail(username);
		session.setAttribute("emp", e);
		model.addAttribute("emp", e);
		return "empHome";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		Employee e = (Employee) session.getAttribute("emp");
		model.addAttribute("emp", e);
		return "empProfile";
	}

	@GetMapping("/addattendance")
	public String registerAttendance(Model model, HttpSession session) {
		LocalDate date = LocalDate.now();
		model.addAttribute("currentDate", date);
		Employee e = (Employee) session.getAttribute("emp");
		model.addAttribute("empid", e.getId());
		return "addattendance";
	}

	@PostMapping("/processattendance")
	public String processAttendance(@ModelAttribute("at") TimeandAttendance at, @RequestParam("id") int id,
			Model model) {
		Employee emp = empserivce.getEmployee(id);
		if (ats.getbydate(at.getDate(), id) != null) {
			model.addAttribute("errorMessage", "You have already marked your attendance for today");
			return "addattendance";
		}
		 if (at.getClockInTime().isAfter(at.getClockOutTime())) {
		        model.addAttribute("error1","Check-in time must be less than check-out time");
		        return "addattendance";
		    }
		
		at.setEmployee(emp);
		ats.saveAttendance(at);
		List<TimeandAttendance> attendanceList = emp.getTimeAndAttendanceList();
		if (attendanceList == null) {
			attendanceList = new ArrayList<>();
		}
		attendanceList.add(at);
		emp.setTimeAndAttendanceList(attendanceList);
		empserivce.saveEmployee(emp);
		model.addAttribute("successMessage", "Attendance added successfully");
		return "redirect:/employees/empHome";
	}

	@GetMapping("/empattendances")
	public String empattendances(Model model, HttpSession session) {
        Employee e = (Employee)session.getAttribute("emp");
        List<TimeandAttendance> l = ats.getempattendance(e);
        model.addAttribute("attendances",l);
		return "empattendances";
	}
}

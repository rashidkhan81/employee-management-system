package com.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ems.dao.EmployeeRepository;
import com.ems.entity.Employee;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository emprepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//getting employee from database using email
		
		Employee emp = emprepo.getEmployeeByUserName(username);
		if(emp==null) {
			throw new UsernameNotFoundException("could not found the user !!");
		}
		CustomUserDetails customuserdetails = new CustomUserDetails(emp);
		return customuserdetails;
	}

}

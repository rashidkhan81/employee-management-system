package com.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import ch.qos.logback.core.encoder.Encoder;

import static org.springframework.security.config.Customizer.withDefaults;

import org.aspectj.weaver.ast.And;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }
	
	  @Bean
	  public UserDetailsService userDetailService(){
		  return new UserDetailsServiceImpl();
	  }
	  @Bean
	    public DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
	        daoauthenticationProvider.setUserDetailsService(userDetailService());
	        daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoauthenticationProvider;
	    }
	
	
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {

        
		     httpsecurity 
		                 .csrf().disable()
		                 .authorizeHttpRequests()
		                 .requestMatchers("/employees/**").hasAnyAuthority("NORMAL","ADMIN")
		                 .requestMatchers("/admin/**").hasAuthority("ADMIN")
		                 .anyRequest()
		                 .authenticated()
		                 .and()
		                 .formLogin()
		                 .permitAll();
		   
		   return httpsecurity.build();
	  }
}

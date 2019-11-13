package com.example.demo.security;

import javax.sql.DataSource;



//import com.example.demo.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.example.demo.dao.UsersRepository;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder getBCPE;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				// find user in db
				.usersByUsernameQuery("select email as username,password ,active from users where email=?")
				// find roles from table roles
				.authoritiesByUsernameQuery(
						"select email,role as authorities from users, users_roles, roles where users.email =? and users.id = users_roles.user_id and users_roles.role_id = roles.id")
				.rolePrefix("ROLE_").passwordEncoder(getBCPE());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/users/login").and().logout().logoutUrl("/users/logout");
		//http.authorizeRequests().antMatchers("/users/*").hasRole("ADMIN");
		//http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");

	}


	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
		
	}
}

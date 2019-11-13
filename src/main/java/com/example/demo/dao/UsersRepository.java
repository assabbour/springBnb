package com.example.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Users;


public interface UsersRepository  extends JpaRepository<Users, Integer>{
	/*@Query("select a from Users a where a.email=:email")
	public Users findByEmail(String email);*/
	//Users findOnebyEmail(final String email);
	 Users findByEmail(String email);

}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Integer>{
	
	
}

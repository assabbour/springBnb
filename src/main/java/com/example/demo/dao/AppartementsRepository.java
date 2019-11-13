package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Param;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Appartements;

public interface AppartementsRepository extends JpaRepository<Appartements, Integer>{
	
	//afficher et filtrer par title
	public Page<Appartements> findByTitleContains(String mc, Pageable pageable);
	
	
	// home page aficher par price 
	@Query(value= "select * from appartements as a where a.price < ?1 and a.price > ?2 ", nativeQuery = true)
	public List<Appartements> findByPrice( BigDecimal maxPrice, BigDecimal minPrice );
	
	
	/*
	  @Query(value= "select * from appartements as a where a.price < ?1 and a.price > ?2 ", nativeQuery = true)
	public List<Appartements> findByPrice( BigDecimal maxPrice, BigDecimal minPrice );
	 * */
	
}

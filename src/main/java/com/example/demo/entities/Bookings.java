package com.example.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Bookings implements Serializable{
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users booking ;
	
	@ManyToOne
	@JoinColumn(name="appartement_id")
	private Appartements bookings ;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	private BigDecimal amount;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	@Lob
	private String description;
	public Bookings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bookings(Integer id, Users booking, Appartements bookings, Date createdAt, BigDecimal amount,
			LocalDate startDate, LocalDate endDate, String description) {
		super();
		this.id = id;
		this.booking = booking;
		this.bookings = bookings;
		this.createdAt = createdAt;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getBooking() {
		return booking;
	}
	public void setBooking(Users booking) {
		this.booking = booking;
	}
	public Appartements getBookings() {
		return bookings;
	}
	public void setBookings(Appartements bookings) {
		this.bookings = bookings;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	/*******************************************                    GEt TOTAL PRICE            *****************************/
	
	public float getTotalPrice(){
		Period p = Period.between(startDate, endDate);
		int nbDays = p.getDays();
		BigDecimal priceBooking = bookings.getPrice();
		float TotalPrice = (nbDays*priceBooking.floatValue());
		
		return TotalPrice;
	}
	
	
	

}

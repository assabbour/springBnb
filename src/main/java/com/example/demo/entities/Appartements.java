package com.example.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
public class Appartements implements Serializable{
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users appartement ;
	
	@OneToMany(mappedBy="comments")
	private Collection<Comments> comment;
	
	@OneToMany(mappedBy="bookings")
	private Collection<Bookings> booking;
	
	@NotEmpty
	@Size(min=5,max=30, message="ce champ ne dois pas etre nul !")
	@Column(name="title")
	private String title;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@NotEmpty
	@Size(min=5,max=255)
	@Column(name="avatar")
	private String avatar;
	
	
	@Column(name="price")
	private BigDecimal price;
	

	@Column(name="city")
	private String city;
	

	@Column(name="adress")
	private String adress;
	
	
	@Column(name="floor")
	private Integer floor;
	
	
	@Column(name="squareMeter")
	private Integer squareMeter;
	
	@Lob
	@Column(name="description")
    private String description;
	public Appartements() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appartements(Integer id, Users appartement, Collection<Comments> comment, Collection<Bookings> booking,
			String title, Date createdAt, String avatar, BigDecimal price, String city, String adress, Integer floor,
			Integer squareMeter, String description) {
		super();
		this.id = id;
		this.appartement = appartement;
		this.comment = comment;
		this.booking = booking;
		this.title = title;
		this.createdAt = createdAt;
		this.avatar = avatar;
		this.price = price;
		this.city = city;
		this.adress = adress;
		this.floor = floor;
		this.squareMeter = squareMeter;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getAppartement() {
		return appartement;
	}
	public void setAppartement(Users appartement) {
		this.appartement = appartement;
	}
	public Collection<Comments> getComment() {
		return comment;
	}
	public void setComment(Collection<Comments> comment) {
		this.comment = comment;
	}
	public Collection<Bookings> getBooking() {
		return booking;
	}
	public void setBooking(Collection<Bookings> booking) {
		this.booking = booking;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getSquareMeter() {
		return squareMeter;
	}
	public void setSquareMeter(Integer squareMeter) {
		this.squareMeter = squareMeter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

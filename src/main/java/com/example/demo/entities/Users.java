package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="users")
public class Users {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastNAme;
	private String email;
	private String password;
	private String passwordConfirm;
	private String adress;
	private int PostalCode;
	private String ville;
	@Lob
	private String description;
	private Date createdAt;
	private String avatar;
	
	private Integer active;
	
	@OneToMany(mappedBy="appartement", fetch=FetchType.LAZY)
	private Collection<Appartements> appartement;
	
	@OneToMany(mappedBy="booking", fetch=FetchType.LAZY)
	private Collection<Bookings> booking;
	
	@OneToMany(mappedBy="comment", fetch=FetchType.LAZY)
	private Collection<Comments> comment;
	
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private Set<Roles> roles;




	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Users(Integer id, String firstName, String lastNAme, String email, String password, String passwordConfirm,
			String adress, int postalCode, String ville, String description, Date createdAt, String avatar,
			Integer active, Collection<Appartements> appartement, Collection<Bookings> booking,
			Collection<Comments> comment, Set<Roles> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastNAme = lastNAme;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.adress = adress;
		PostalCode = postalCode;
		this.ville = ville;
		this.description = description;
		this.createdAt = createdAt;
		this.avatar = avatar;
		this.active = active;
		this.appartement = appartement;
		this.booking = booking;
		this.comment = comment;
		this.roles = roles;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastNAme() {
		return lastNAme;
	}




	public void setLastNAme(String lastNAme) {
		this.lastNAme = lastNAme;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getPasswordConfirm() {
		return passwordConfirm;
	}




	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}




	public String getAdress() {
		return adress;
	}




	public void setAdress(String adress) {
		this.adress = adress;
	}




	public int getPostalCode() {
		return PostalCode;
	}




	public void setPostalCode(int postalCode) {
		PostalCode = postalCode;
	}




	public String getVille() {
		return ville;
	}




	public void setVille(String ville) {
		this.ville = ville;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
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




	public Integer getActive() {
		return active;
	}




	public void setActive(Integer active) {
		this.active = active;
	}




	public Collection<Appartements> getAppartement() {
		return appartement;
	}




	public void setAppartement(Collection<Appartements> appartement) {
		this.appartement = appartement;
	}




	public Collection<Bookings> getBooking() {
		return booking;
	}




	public void setBooking(Collection<Bookings> booking) {
		this.booking = booking;
	}




	public Collection<Comments> getComment() {
		return comment;
	}




	public void setComment(Collection<Comments> comment) {
		this.comment = comment;
	}




	public Set<Roles> getRoles() {
		return roles;
	}




	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	

	
	
}

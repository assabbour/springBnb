package com.example.demo.entities;

import java.io.Serializable;
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
public class Comments implements Serializable{

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date createdAt;
	@Lob
	private String description;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private Users comment ;
	
	@ManyToOne
	@JoinColumn(name="AppartementId")
	private Appartements comments ;

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(Integer id, Date createdAt, String description, Users comment, Appartements comments) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.description = description;
		this.comment = comment;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getComment() {
		return comment;
	}

	public void setComment(Users comment) {
		this.comment = comment;
	}

	public Appartements getComments() {
		return comments;
	}

	public void setComments(Appartements comments) {
		this.comments = comments;
	}

	
	
}

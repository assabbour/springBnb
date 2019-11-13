package com.example.demo.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table(name="roles")
public class Roles {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String role;
	

	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<Users> users;


	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Roles(Integer id, String role, Set<Users> users) {
		super();
		this.id = id;
		this.role = role;
		this.users = users;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Set<Users> getUsers() {
		return users;
	}


	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	
	

}

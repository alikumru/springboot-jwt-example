package com.ali.doctors.Doktorlar.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="APPLICATIONUSER")
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = true)
	private Long id;
	@Column(name = "username",nullable=false)
	private String username;
	@Column(name = "password",nullable=false)
	private String password;
	@Column(name = "userRole",nullable=false)
	private String userRole;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public ApplicationUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public ApplicationUser(String username, String password,String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	public ApplicationUser() {
		
	}

	
}

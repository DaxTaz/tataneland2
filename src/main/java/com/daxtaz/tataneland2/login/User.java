package com.daxtaz.tataneland2.login;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

	private Integer id;
	
	private String email;
	
	private String password;
	
	private List<Role> roles = new ArrayList<Role>();
	
	public User() {}

	public User(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EMAIL", nullable = false, unique = true)
	@NotBlank(message = "User email can not be null")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false)
	@NotBlank(message = "User password can not be null")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@ManyToMany
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}

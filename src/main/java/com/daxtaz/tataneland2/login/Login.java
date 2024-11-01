package com.daxtaz.tataneland2.login;

import javax.validation.constraints.NotBlank;

public class Login {

	private String email;
	
	private String password;

	@NotBlank(message = "Email can not be null")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message = "Password can not be null")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

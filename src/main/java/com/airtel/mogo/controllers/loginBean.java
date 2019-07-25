package com.airtel.mogo.controllers;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;



public class loginBean {
	
	@NotBlank(message = "username Can't be Empty")
	@Size(min=5, max=20, message = "username size should be between 5 to 20")
	private String username;
	
	@NotBlank(message = "password Can't be Empty")
	@Size(min=5, max=20, message = "password size should be between 5 to 20")
	private String password;
	
	
	
	public loginBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public loginBean() {
		super();
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
	

}

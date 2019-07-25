package com.airtel.mogo.model;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {


	
	@Id
private String id;
private String name;
private String city;
private String age;
private String aadhaar;



public Employee() {
	super();
}

@JsonCreator
public Employee( @JsonProperty("Id") String id, @JsonProperty("name") String name, @JsonProperty("city") String city, @JsonProperty("age") String age, @JsonProperty("aadhaar") String aadhaar) {
	super();
	this.id = id;
	this.name = name;
	this.city = city;
	this.age = age;
	this.aadhaar = aadhaar;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getAadhaar() {
	return aadhaar;
}
public void setAadhaar(String aadhaar) {
	this.aadhaar = aadhaar;
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	
	StringBuilder str = new StringBuilder();
	  str.append("Employee Id:- " + getId());
	  str.append(" Name:- " + getName());
	  str.append(" City:- " + getCity());
	  str.append(" Age:- " + getAge());
	  str.append(" Aadhaar:- " + getAadhaar());
	  return str.toString();
	
}



}

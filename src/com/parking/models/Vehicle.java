package com.parking.models;

import java.io.Serializable;

public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = -2233528989350623992L;
	private String color;
	private String registrationNumber;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}

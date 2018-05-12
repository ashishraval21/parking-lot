package com.parking.models;

import java.io.Serializable;

public class Slot implements Serializable{
	private static final long serialVersionUID = 1298928757356310776L;
	private Integer slotNumber;
	private Vehicle vehicle;

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}

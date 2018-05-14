package com.parking.test;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.parking.impl.ParkingImplementation;
import com.parking.models.Vehicle;
import com.parking.util.Message;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestApplication {
	private ParkingImplementation impl;
	private Vehicle vehicle;
	private int n = 6;
	private TreeSet<Integer> totalSlots;

	@Before
	public void createParking() {
		impl = new ParkingImplementation(n);
		vehicle = new Vehicle();
		totalSlots = new TreeSet<>();
		for (int i = 1; i <= n; i++) {
			totalSlots.add(i);
		}
	}

	@Test
	public void initialize() {
		assertEquals("Created a parking lot with " + n + " slots", impl.iniatilizeMessage());
	}

	@Test
	public void initializeAddingCar() {
			inputData(impl);
	}

	private void inputData(ParkingImplementation impl) {
		vehicle.setColor("red");
		vehicle.setRegistrationNumber("KA-06-3456");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("white");
		vehicle.setRegistrationNumber("KA-06-1234");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("red");
		vehicle.setRegistrationNumber("KA-06-2345");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("green");
		vehicle.setRegistrationNumber("KA-06-4567");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("blue");
		vehicle.setRegistrationNumber("KA-06-3009");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("black");
		vehicle.setRegistrationNumber("KA-06-109");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("blue");
		vehicle.setRegistrationNumber("KA-06-309");
		assertEquals(String.format(Message.FULL, totalSlots.pollFirst()), impl.parkSlot(vehicle));
	}
	
	@Test
	public void initializefreeSlot() {
		inputData(impl);
		int free = 5;
		assertEquals(String.format(Message.FREE_SLOT_MESSAGE, free), impl.freeSlot(--free));
		totalSlots.add(++free);
		free = 3;
		assertEquals(String.format(Message.FREE_SLOT_MESSAGE, free), impl.freeSlot(--free));
		totalSlots.add(++free);
	}
	
	@Test 
	public void initializeinsertAgain() {
		vehicle = new Vehicle();
		vehicle.setColor("pink");
		vehicle.setRegistrationNumber("KA-06-109");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));

		vehicle = new Vehicle();
		vehicle.setColor("yellow");
		vehicle.setRegistrationNumber("KA-06-309");
		assertEquals(String.format(Message.PARK_SLOT_MESSAGE, totalSlots.pollFirst()), impl.parkSlot(vehicle));
	}
	
	@Test public void initializeQuery() {
		
	}
}

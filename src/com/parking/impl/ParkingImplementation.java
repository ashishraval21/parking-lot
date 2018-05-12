package com.parking.impl;

import com.parking.models.Slot;
import com.parking.models.Vehicle;
import com.parking.util.Message;

public class ParkingImplementation {
	Slot[] totalSlots;
	int size;
	int[] indexArray;
	int last, free;

	public int getSize() {
		return size;
	}

	public ParkingImplementation(int size) {
		this.totalSlots = new Slot[size];
		this.size = size;
		this.indexArray = new int[size];
		this.last = -1;
		this.free = 0;

		for (int i = 0; i < size; i++) {
			indexArray[i] = i + 1;
		}
		indexArray[size - 1] = -1;
		System.out.println(String.format(Message.CREATED_SLOT_MESSAGE, size));
	}

	public boolean isFull() {
		return free == -1;
	}

	public boolean isEmpty(int index) {
		return totalSlots[index].getSlotNumber() == null;
	}

	public String parkSlot(Vehicle vehicle) {
		if (isFull())
			return Message.FULL;
		int i = free;
		free = indexArray[i];
		indexArray[i] = last;
		last = i;
		totalSlots[i] = new Slot();
		totalSlots[i].setSlotNumber(i);
		totalSlots[i].setVehicle(vehicle);
		return String.format(Message.PARK_SLOT_MESSAGE, i + 1);
	}

	public String freeSlot(int index) {
		if (isEmpty(index))
			return String.format(Message.ALREADY_FREE_SLOT_MESSAGE, index + 1);

		int i = index;
		last = indexArray[i];
		indexArray[i] = free;
		free = i;
		totalSlots[i] = null;
		return String.format(Message.FREE_SLOT_MESSAGE, index + 1);
	}

	public void display() {
		System.out.println(Message.STATUS_HEADER);
		for (Slot s : totalSlots) {
			if (s == null)
				continue;
			System.out.println((s.getSlotNumber()+1)+" "+s.getVehicle().getRegistrationNumber()+" "+s.getVehicle().getColor());
		}

	}

}

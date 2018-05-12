package com.parking.impl;

import com.parking.models.Slot;
import com.parking.models.Vehicle;
import com.parking.util.Message;

public class ParkingImplementation {
	Slot[] totalSlots;
	int size;
	int[] indexArray;
	int last, free;

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
	}

	public boolean isFull() {
		return free == -1;
	}

	public boolean isEmpty( int index) {
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
		return String.format(Message.parkSlotMessage, i+1);
	}

	public String freeSlot(int index) {
		if(isEmpty(index))
			return String.format(Message.alreadyFreeSlotMessage, index+1);
		
		int i = last;
		last = indexArray[i];
		indexArray[i] = free;
		free = i;
		totalSlots[i] = null;
		return String.format(Message.freeSlotMessage, index+1);
	}
	
	

}

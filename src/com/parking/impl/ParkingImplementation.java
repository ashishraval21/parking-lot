package com.parking.impl;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.parking.models.Slot;
import com.parking.models.Vehicle;
import com.parking.util.CommandEnum;
import com.parking.util.Message;

public class ParkingImplementation {
	Slot[] totalSlots;
	int size;
	int[] indexArray;
	int last, free;
	HashMap<String, TreeSet<Integer>> queryMap;

	public int getSize() {
		return size;
	}

	public ParkingImplementation(int size) {
		this.totalSlots = new Slot[size];
		this.size = size;
		this.indexArray = new int[size];
		this.last = -1;
		this.free = 0;
		queryMap = new HashMap<>();

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
		return index < size ? totalSlots[index] == null : false;
	}

	public String parkSlot(Vehicle vehicle) {
		if (isFull())
			return Message.FULL;
		int index = free;
		free = indexArray[index];
		indexArray[index] = last;
		last = index;
		totalSlots[index] = new Slot();
		totalSlots[index].setSlotNumber(index);
		totalSlots[index].setVehicle(vehicle);

		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(index);
		queryMap.put(vehicle.getRegistrationNumber(), treeSet);
		if (queryMap.containsKey(vehicle.getColor()))
			queryMap.get(vehicle.getColor()).add(index);
		else
			queryMap.put(vehicle.getColor(), treeSet);
		return String.format(Message.PARK_SLOT_MESSAGE, index + 1);
	}

	public String freeSlot(int index) {
		if (isEmpty(index))
			return String.format(Message.ALREADY_FREE_SLOT_MESSAGE, index + 1);

		int i = index;
		last = indexArray[i];
		indexArray[i] = free;
		free = i;
		queryMap.get(totalSlots[i].getVehicle().getColor()).remove(totalSlots[i].getSlotNumber());
		queryMap.remove(totalSlots[i].getVehicle().getRegistrationNumber());
		totalSlots[i] = null;
		return String.format(Message.FREE_SLOT_MESSAGE, index + 1);
	}

	public void display() {
		System.out.println(Message.STATUS_HEADER);
		for (Slot s : totalSlots) {
			if (s == null)
				continue;
			System.out.println((s.getSlotNumber() + 1) + " " + s.getVehicle().getRegistrationNumber() + " "
					+ s.getVehicle().getColor());
		}

	}

	public static void showErrorMessage() {
		System.out.println(Message.ERROR_MESSAGE);
	}

	public void runQuery(CommandEnum task, String key) {
		switch (task) {

		case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
			if (queryMap.containsKey(key)) {
				String result = queryMap.get(key).stream().map(n -> totalSlots[n].getVehicle().getRegistrationNumber())
						.collect(Collectors.joining(", "));
				System.out.println(result);
			} else
				System.out.println(Message.NOT_FOUND);
			break;
		case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
			if (queryMap.containsKey(key)) {
				System.out.println(queryMap.get(key).first() + 1);
			} else
				System.out.println(Message.NOT_FOUND);
			break;
		case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
			String result = queryMap.get(key).stream().map(n -> n + 1 + "").collect(Collectors.joining(", "));
			System.out.println(result);
			break;
		default:
			break;
		}

	}

}

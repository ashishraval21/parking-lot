package com.parking.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.parking.impl.ParkingImplementation;
import com.parking.models.Vehicle;
import com.parking.util.CommandEnum;

public class ParkingSlot {

	public static void main(String[] args) {
		ParkingImplementation parking = null;
		Scanner sc;
		if (args.length == 1)
			try {
				sc = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e1) {
				System.out.println("File not found at location path : "+args[0]);
				return;
			}
		else
			sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] operation = line.split(" ");

			CommandEnum which = CommandEnum.getName(operation[0]);

			switch (which) {
			case CREATE_PARKING_LOT:
				if (parking == null && operation.length == 2) {
					try {
						int size = Integer.parseInt(operation[1]);
						parking = new ParkingImplementation(size);
					} catch (NumberFormatException e) {
						System.out.println("argument cannot be converted into a number : " + operation[1]);
					}
				} else {
					if (parking != null) {
						System.out.println("parking space already initiated with space of " + parking.getSize());
					} else {
						System.out.println("parameter mismatched for creating parking space");
					}
				}
				break;
			case PARK:
				if (operation.length == 3) {
					Vehicle v = new Vehicle();
					v.setRegistrationNumber(operation[1]);
					v.setColor(operation[2]);
					System.out.println(parking.parkSlot(v));
				} else {
					System.out.println("parameter mismatched for park a vehicle");
				}
				break;
			case LEAVE:
				if (operation.length == 2) {
					try {
						int index = Integer.parseInt(operation[1]);
						System.out.println(parking.freeSlot(index - 1));
					} catch (NumberFormatException e) {
						System.out.println("argument cannot be converted into a number : " + operation[1]);
					}
				} else {
					System.out.println("parameter mismatched for free slot");
				}
				break;
			case STATUS:
				parking.display();
				break;
			case REGISTRATION_NUMBER_WITH_COLOR:
				break;
			case SLOT_NUMBER_FOR_REGISTRATION:
				break;
			case SLOT_NUMBER_WITH_COLOR:
				break;
			case DEFAULT:
				break;

			default:
				break;
			}
		}
		sc.close();

	}

}

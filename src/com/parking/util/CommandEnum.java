package com.parking.util;

public enum CommandEnum {

	CREATE_PARKING_LOT("create_parking_lot"),
	PARK("park"),
	LEAVE("leave"),
	STATUS("status"),
	REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
	SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
	SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number"),
	DEFAULT("");

	public String name;

	private CommandEnum(String name) {
		this.name = name;
	}

	public static CommandEnum getName(String value) {
		for(CommandEnum e : CommandEnum.values()) {
			if(value.equals(e.name))
				return e;
		}
		return CommandEnum.DEFAULT;
	}

}

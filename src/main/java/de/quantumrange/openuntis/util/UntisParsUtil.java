package de.quantumrange.openuntis.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalTime;

public class UntisParsUtil {

	public static LocalTime parseTime(JsonNode node) {
		return parseTime(node.asInt());
	}

	public static LocalTime parseTime(int time) {
		String str = String.valueOf(time);

		return str.length() == 3 ?
				LocalTime.of(Integer.parseInt(str.substring(0, 1)), Integer.parseInt(str.substring(1, 3))) :
				LocalTime.of(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(2, 4)));
	}

	public static LocalDate parseDate(int date) {
		return null;
	}

}

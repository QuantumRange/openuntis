package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;
import de.quantumrange.openuntis.util.UntisParsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

import static de.quantumrange.openuntis.util.UntisParsUtil.*;

public class TimeTable {

	private final int id;
	private final LocalDate date;
	private final LocalTime startTime, endTime;
	private final int[] schoolClasses, teachers, subjects, rooms;
	private final String activityType;

	public TimeTable(JsonNode node) {
		this.id = node.get("id").asInt();
		this.date = parseDate(node.get("date"));
		this.startTime = parseTime(node.get("startTime"));
		this.endTime = parseTime(node.get("endTime"));

		this.schoolClasses = parseArray(node.get("kl"));
		this.teachers = parseArray(node.get("te"));
		this.subjects = parseArray(node.get("su"));
		this.rooms = parseArray(node.get("ro"));

		this.activityType = node.get("activityType").asText();
	}

	private int[] parseArray(JsonNode node) {
		int[] arr = new int[node.size()];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = node.get(i).get("id").asInt();
		}

		return arr;
	}

	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public int[] getSchoolClasses() {
		return schoolClasses;
	}

	public int[] getTeachers() {
		return teachers;
	}

	public int[] getSubjects() {
		return subjects;
	}

	public int[] getRooms() {
		return rooms;
	}

	public String getActivityType() {
		return activityType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TimeTable timeTable = (TimeTable) o;
		return getId() == timeTable.getId() && Objects.equals(getDate(), timeTable.getDate()) && Objects.equals(getStartTime(), timeTable.getStartTime()) && Objects.equals(getEndTime(), timeTable.getEndTime()) && Arrays.equals(getSchoolClasses(), timeTable.getSchoolClasses()) && Arrays.equals(getTeachers(), timeTable.getTeachers()) && Arrays.equals(getSubjects(), timeTable.getSubjects()) && Arrays.equals(getRooms(), timeTable.getRooms()) && Objects.equals(getActivityType(), timeTable.getActivityType());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getDate(), getStartTime(), getEndTime(), getActivityType());
		result = 31 * result + Arrays.hashCode(getSchoolClasses());
		result = 31 * result + Arrays.hashCode(getTeachers());
		result = 31 * result + Arrays.hashCode(getSubjects());
		result = 31 * result + Arrays.hashCode(getRooms());
		return result;
	}

	@Override
	public String toString() {
		return "TimeTable{" +
				"id=" + id +
				", date=" + date +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", schoolClasses=" + Arrays.toString(schoolClasses) +
				", teachers=" + Arrays.toString(teachers) +
				", subjects=" + Arrays.toString(subjects) +
				", rooms=" + Arrays.toString(rooms) +
				", activityType='" + activityType + '\'' +
				'}';
	}
}

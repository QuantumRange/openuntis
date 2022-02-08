package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;
import de.quantumrange.openuntis.util.UntisParsUtil;

import java.time.LocalDate;
import java.util.Objects;

public class Holiday {

	private final int id;
	private final String name, longName;
	private final LocalDate startDate, endDate;

	public Holiday(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.longName = node.get("longName").asText();
		this.startDate = UntisParsUtil.parseDate(node.get("startDate"));
		this.endDate = UntisParsUtil.parseDate(node.get("endDate"));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLongName() {
		return longName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Holiday holiday = (Holiday) o;
		return getId() == holiday.getId() && Objects.equals(getName(), holiday.getName()) && Objects.equals(getLongName(), holiday.getLongName()) && Objects.equals(getStartDate(), holiday.getStartDate()) && Objects.equals(getEndDate(), holiday.getEndDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getLongName(), getStartDate(), getEndDate());
	}

	@Override
	public String toString() {
		return "Holiday{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longName='" + longName + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}

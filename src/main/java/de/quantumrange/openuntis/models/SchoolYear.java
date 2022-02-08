package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;
import de.quantumrange.openuntis.util.UntisParsUtil;

import java.time.LocalDate;
import java.util.Objects;

public class SchoolYear {

	private final int id;
	private final String name;
	private final LocalDate startDate, endDate;

	public SchoolYear(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.startDate = UntisParsUtil.parseDate(node.get("startDate"));
		this.endDate = UntisParsUtil.parseDate(node.get("endDate"));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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
		SchoolYear that = (SchoolYear) o;
		return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getStartDate(), getEndDate());
	}

	@Override
	public String toString() {
		return "SchoolYear{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}

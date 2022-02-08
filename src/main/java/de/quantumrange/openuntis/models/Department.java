package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public class Department {

	/*
	  {
    "id": 2,
    "name": "AL BG",
    "longName": "Berufliches Gymnasium Technik"
  },
	 */

	private final int id;
	private final String name, longName;

	public Department(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.longName = node.get("name").asText();
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Department that = (Department) o;
		return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getLongName(), that.getLongName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getLongName());
	}

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longName='" + longName + '\'' +
				'}';
	}
}

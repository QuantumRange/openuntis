package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public class Student {

	private final int id;
	private final String name, foreName, longName, gender;

	public Student(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.foreName = node.get("foreName").asText();
		this.longName = node.get("longName").asText();
		this.gender = node.get("gender").asText();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getForeName() {
		return foreName;
	}

	public String getLongName() {
		return longName;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return getId() == student.getId() && Objects.equals(getName(), student.getName()) && Objects.equals(getForeName(), student.getForeName()) && Objects.equals(getLongName(), student.getLongName()) && Objects.equals(getGender(), student.getGender());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getForeName(), getLongName(), getGender());
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", foreName='" + foreName + '\'' +
				", longName='" + longName + '\'' +
				", gender='" + gender + '\'' +
				'}';
	}
}

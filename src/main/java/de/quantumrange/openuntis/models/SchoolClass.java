package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SchoolClass implements Serializable {

	private final int id;
	private final String name, longName;
	private final boolean active;
	private final int did;
	private final Integer[] teachers;

	public SchoolClass(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.longName = node.get("longName").asText();
		this.active = node.get("active").asBoolean();
		this.did = node.get("did") == null ? -1 : node.get("did").asInt();

		List<Integer> teacherList = new ArrayList<>();

		int i = 0;

		while (true) {
			i++;

			if (node.get("teacher" + i) != null) {
				teacherList.add(node.get("teacher" + i).asInt());
			} else break;
		}

		this.teachers = teacherList.toArray(Integer[]::new);
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

	public boolean isActive() {
		return active;
	}

	public int getDid() {
		return did;
	}

	public Integer[] getTeachers() {
		return teachers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SchoolClass that = (SchoolClass) o;
		return getId() == that.getId() && isActive() == that.isActive() && getDid() == that.getDid() && Objects.equals(getName(), that.getName()) && Objects.equals(getLongName(), that.getLongName()) && Arrays.equals(getTeachers(), that.getTeachers());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getName(), getLongName(), isActive(), getDid());
		result = 31 * result + Arrays.hashCode(getTeachers());
		return result;
	}

	@Override
	public String toString() {
		return "SchoolClass{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longName='" + longName + '\'' +
				", active=" + active +
				", did=" + did +
				", teachers=" + Arrays.toString(teachers) +
				'}';
	}
}

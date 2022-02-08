package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Teacher implements Serializable {

	@Serial
	private static final long serialVersionUID = -4131751679869706913L;

	private final int id;
	private final String name, foreName, longName, title;
	private final boolean active;
	private final int[] dids;

	public Teacher(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.foreName = node.get("foreName").asText();
		this.longName = node.get("longName").asText();
		this.title = node.get("title").asText();
		this.active = node.get("active").asBoolean();
		this.dids = new int[node.get("dids").size()];

		for (int i = 0; i < this.dids.length; i++) {
			this.dids[i] = node.get("dids").get(i).asInt();
		}
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

	public String getTitle() {
		return title;
	}

	public boolean isActive() {
		return active;
	}

	public int[] getDids() {
		return dids;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Teacher teacher = (Teacher) o;
		return getId() == teacher.getId() && isActive() == teacher.isActive() && Objects.equals(getName(), teacher.getName()) && Objects.equals(getForeName(), teacher.getForeName()) && Objects.equals(getLongName(), teacher.getLongName()) && Objects.equals(getTitle(), teacher.getTitle()) && Arrays.equals(getDids(), teacher.getDids());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getName(), getForeName(), getLongName(), getTitle(), isActive());
		result = 31 * result + Arrays.hashCode(getDids());
		return result;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", foreName='" + foreName + '\'' +
				", longName='" + longName + '\'' +
				", title='" + title + '\'' +
				", active=" + active +
				", dids=" + Arrays.toString(dids) +
				'}';
	}
}

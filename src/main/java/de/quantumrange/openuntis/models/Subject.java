package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {

	@Serial
	private static final long serialVersionUID = -5247408380537914682L;

	private final int id;
	private final String name, longName, alternateName;
	private final boolean active;

	public Subject(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.longName = node.get("longName").asText();
		this.alternateName = node.get("alternateName").asText();
		this.active = node.get("active").asBoolean();
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

	public String getAlternateName() {
		return alternateName;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Subject subject = (Subject) o;
		return getId() == subject.getId() && isActive() == subject.isActive() && Objects.equals(getName(), subject.getName()) && Objects.equals(getLongName(), subject.getLongName()) && Objects.equals(getAlternateName(), subject.getAlternateName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getLongName(), getAlternateName(), isActive());
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longName='" + longName + '\'' +
				", alternateName='" + alternateName + '\'' +
				", active=" + active +
				'}';
	}
}

package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

	@Serial
	private static final long serialVersionUID = -5878055751131209884L;

	private final long id, did;
	private final String name;
	private final String longName;
	private final String building;
	private final boolean active;

	public Room(JsonNode node) {
		this.id = node.get("id").asInt();
		this.name = node.get("name").asText();
		this.longName = node.get("longName").asText();
		this.active = node.get("active").asBoolean();
		this.building = node.get("building").asText();
		this.did = node.get("did") == null ? -1 : node.get("did").asInt();
	}

	public long getId() {
		return id;
	}

	public long getDid() {
		return did;
	}

	public String getName() {
		return name;
	}

	public String getLongName() {
		return longName;
	}

	public String getBuilding() {
		return building;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return getId() == room.getId() && getDid() == room.getDid() && isActive() == room.isActive() && Objects.equals(getName(), room.getName()) && Objects.equals(getLongName(), room.getLongName()) && Objects.equals(getBuilding(), room.getBuilding());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDid(), getName(), getLongName(), getBuilding(), isActive());
	}

	@Override
	public String toString() {
		return "Room{" +
				"id=" + id +
				", did=" + did +
				", name='" + name + '\'' +
				", longName='" + longName + '\'' +
				", building='" + building + '\'' +
				", active=" + active +
				'}';
	}
}

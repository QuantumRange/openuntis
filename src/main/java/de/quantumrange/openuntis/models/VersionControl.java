package de.quantumrange.openuntis.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public abstract class VersionControl<T> extends ArrayList<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 6699953500655502898L;

	public VersionControl(int initialCapacity) {
		super(initialCapacity);
	}

	public abstract boolean equals(T obj1, T obj2);

	public boolean checkForUpdates(T obj) {
		if (lastest().isEmpty()) {
			add(obj);
			return true;
		}

		if (!equals(obj, lastest().get())) {
			add(obj);
			return true;
		}

		return false;
	}

	public Optional<T> lastest() {
		if (isEmpty()) return Optional.empty();

		return Optional.of(get(size() - 1));
	}

	public Optional<T> first() {
		if (isEmpty()) return Optional.empty();

		return Optional.of(get(0));
	}

}

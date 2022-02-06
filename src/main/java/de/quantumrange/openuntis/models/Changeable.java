package de.quantumrange.openuntis.models;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 *
 * @param <T> is the class itself that implements this.
 */
public interface Changeable<T> extends Comparable<Changeable<T>> {

	T getCurrent();
	List<T> getHistory();
	T getFirst();
	int getChangeID();

	@Override
	default int compareTo(@NotNull Changeable<T> o) {
		return Integer.compare(getChangeID(), o.getChangeID());
	}
}

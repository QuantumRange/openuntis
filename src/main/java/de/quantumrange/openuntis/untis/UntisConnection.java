package de.quantumrange.openuntis.untis;

import de.quantumrange.openuntis.models.TimeGrid;

public interface UntisConnection {

	boolean isConnected();

	TimeGrid getTimeGrid();

	void connect();
	void disconnect();

}

package de.quantumrange.openuntis.untis;

import de.quantumrange.openuntis.models.Room;
import de.quantumrange.openuntis.models.TimeGrid;

public interface UntisConnection {

	boolean isConnected();

	TimeGrid getTimeGrid();
	Room[] getRooms();

	void connect();
	void disconnect();

}

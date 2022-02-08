package de.quantumrange.openuntis.untis;

import de.quantumrange.openuntis.models.*;

public interface UntisConnection {

	boolean isConnected();

	TimeGrid getTimeGrid();
	Room[] getRooms();
	SchoolClass[] getClasses();
	Subject[] getSubjects();
	Teacher[] getTeachers();
	Holiday[] getHolidays();
	SchoolYear getCurrentSchoolYear();

	void connect();
	void disconnect();

}

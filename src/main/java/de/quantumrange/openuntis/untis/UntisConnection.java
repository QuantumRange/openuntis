package de.quantumrange.openuntis.untis;

import de.quantumrange.openuntis.models.*;

import java.time.LocalDate;

public interface UntisConnection {

	boolean isConnected();

	TimeGrid getTimeGrid();
	Room[] getRooms();
	SchoolClass[] getClasses();
	Subject[] getSubjects();
	Teacher[] getTeachers();
	Holiday[] getHolidays();
	TimeTable[] getTimeTable(LocalDate from, LocalDate to);
	SchoolYear getCurrentSchoolYear();
	Department[] getDepartments();
	Student[] getStudents();

	/*
	{"sessionId":"A1C6EB65966324AD348E3AAA47726B4B","personType":5,"personId":2337,"klasseId":2460}

	GET
	/WebUntis/api/exams
	params: {
                startDate: this.convertDateToUntis(rangeStart),
                endDate: this.convertDateToUntis(rangeEnd),
                klasseId: klasseId,
                withGrades: withGrades
            }

	If GET

	_buildCookies() {
		let cookies = [];
		cookies.push(CookieBuilder.serialize('JSESSIONID', this.sessionInformation.sessionId));
		cookies.push(CookieBuilder.serialize('schoolname', this.schoolbase64));
		return cookies.join('; ');
	}


	method: 'GET',
            url: `/WebUntis/api/public/news/newsWidgetData`,
            params: {
                date: this.convertDateToUntis(date),
            },
            headers: {
                Cookie: this._buildCookies(),
            },


            method: 'GET',
            url: `/WebUntis/api/rest/view/v1/messages`,
            headers: {
                Authorization: `Bearer ${this.sessionInformation.jwt_token}`,
                Cookie: this._buildCookies(),
            },



            method: 'GET',
            url: `/WebUntis/api/token/new`,
            headers: {
                //Authorization: `Bearer ${this._getToken()}`,
                Cookie: this._buildCookies(),
            },


	 */

	void connect();
	void disconnect();

	default TimeTable[] getCurrentTimeTable() {
		LocalDate now = LocalDate.now();
		LocalDate from = now.minusDays(now.getDayOfWeek().getValue() - 1);
		LocalDate to = now.plusDays(7 - now.getDayOfWeek().getValue());

		return getTimeTable(from, to);
	}

}

package de.quantumrange.openuntis.untis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.quantumrange.openuntis.models.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WebUntisConnection implements UntisConnection {


	@Nullable
	private String session;

	private int type;
	private int studentID;
	private int classID;

	private final @NotNull String id;
	private final @NotNull UntisAccess access;
	private final @NotNull WebClient client;

	public WebUntisConnection(UntisAccess access) {
		this.access = access;
		this.id = generateId();
		this.client = WebClient.builder()
				.baseUrl((access.server().startsWith("http") ? "" : "https://") + access.server())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Override
	public boolean isConnected() {
		return session != null;
	}

	@Override
	public TimeGrid getTimeGrid() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getTimegridUnits"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		return new TimeGrid(response);
	}

	@Override
	public Room[] getRooms() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getRooms"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		Room[] rooms = new Room[response.size()];

		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = new Room(response.get(i));
		}

		return rooms;
	}

	@Override
	public void connect() {
		JsonNode response = parseResponse(client.post()
				.uri("/WebUntis/jsonrpc.do?school=" + access.school())
				.bodyValue(requestHelper("authenticate", access.user(), access.password(), "web"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		try {
			this.type = response.get("personType").asInt();
			this.studentID = response.get("personId").asInt();
			this.classID = response.get("klasseId").asInt();
			this.session = response.get("sessionId").asText();
		} catch (Exception e) {
			this.session = null;
			disconnect();
		}
	}

	@Override
	public SchoolClass[] getClasses() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getKlassen"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		SchoolClass[] classes = new SchoolClass[response.size()];

		for (int i = 0; i < classes.length; i++) {
			classes[i] = new SchoolClass(response.get(i));
		}

		return classes;
	}

	@Override
	public Subject[] getSubjects() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getSubjects"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		Subject[] subjects = new Subject[response.size()];

		for (int i = 0; i < subjects.length; i++) {
			subjects[i] = new Subject(response.get(i));
		}

		return subjects;
	}

	@Override
	public Teacher[] getTeachers() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getTeachers"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		Teacher[] teachers = new Teacher[response.size()];

		for (int i = 0; i < teachers.length; i++) {
			teachers[i] = new Teacher(response.get(i));
		}

		return teachers;
	}

	@Override
	public Holiday[] getHolidays() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getHolidays"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		Holiday[] holidays = new Holiday[response.size()];

		for (int i = 0; i < holidays.length; i++) {
			holidays[i] = new Holiday(response.get(i));
		}

		return holidays;
	}

	@Override
	public SchoolYear getCurrentSchoolYear() {
		JsonNode response = parseResponse(client.post()
				.uri(getRequestUrl())
				.bodyValue(requestHelper("getCurrentSchoolyear"))
				.retrieve()
				.bodyToMono(String.class)
				.block());

		return new SchoolYear(response);
	}

	private @NotNull JsonNode parseResponse(String data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(data);

			return node.get("result");
		} catch (JsonProcessingException e) {
			System.out.println("Error: " + data);
			e.printStackTrace();
			return null;
		}
	}

	private String requestHelper(String method, Object... params) {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> data = new HashMap<>();

		data.put("jsonrpc", "2.0");
		data.put("id", id);
		data.put("method", method);
		data.put("params", params);

		try {
			return objectMapper.writer().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void disconnect() {
		if (this.session != null) {
			client.post()
					.uri(getRequestUrl())
					.bodyValue(requestHelper("logout"))
					.retrieve()
					.toBodilessEntity()
					.block();

			this.session = null;
		}

		this.type = 0;
		this.studentID = 0;
		this.classID = 0;
	}

	private @NotNull String getRequestUrl() {
		return "WebUntis/jsonrpc.do;jsessionid=%s?school=%s".formatted(session, access.school());
	}

	private @NotNull String generateId() {
		try {
			Random rnd = new Random();
			byte[] bytes = new byte[20];
			rnd.nextBytes(bytes);
			MessageDigest d = MessageDigest.getInstance("MD5");
			return new String(d.digest(bytes), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			throw new IllegalStateException();
		}
	}
}

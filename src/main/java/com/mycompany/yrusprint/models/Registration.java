/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author tempus
 */
public class Registration {
	String bibId;
    Runner runner;
	LocalDate date;
	String eventId;
	String raceCategory;

	public Registration(String bibId, Runner runner, LocalDate date, String eventId, String raceCategory) {
		this.bibId = bibId;
		this.runner = runner;
		this.date = date;
		this.eventId = eventId;
		this.raceCategory = raceCategory;
	}

	public Registration(Map<String, Object> registrationEntry) {
		this.bibId = (String)registrationEntry.get("bibId");
		this.runner = new Runner((Map<String, Object>)registrationEntry.get("runner"));
		this.date = LocalDate.parse((String)registrationEntry.get("date"));
		this.eventId = (String)registrationEntry.get("eventId");
		this.raceCategory = (String)registrationEntry.get("raceCategory");
	}

	public String getBibId() {
		return bibId;
	}

	public Runner getRunner() {
		return runner;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getEventId() {
		return Integer.parseInt(eventId);
	}

	public String getRaceCategory() {
		return raceCategory;
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tempus
 */
public class RaceEvent {
	private int id;
	private String name;
	private String description;
	private String date;
	private List<RaceCategory> raceCategories;

	public RaceEvent(Map<String, Object> eventEntry) {
		this.id = (Integer)eventEntry.get("id");
		this.name = (String)eventEntry.get("name");
		this.description = (String)eventEntry.get("description");
		this.date = (String)eventEntry.get("date");

		this.raceCategories = new ArrayList<>();
		for (Map<String, Object> category : (List<Map<String, Object>>) eventEntry.get("raceCategories")) {
			this.raceCategories.add(new RaceCategory(
				(String)category.get("categoryName"), 
				(Double)category.get("registrationFee"), 
				(int)category.get("minimumAge"), 
				(int)category.get("slotLimit"),
				(int)category.get("registeredSlots")
			));	
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public List<RaceCategory> getRaceCategories() {
		return raceCategories;
	}
}

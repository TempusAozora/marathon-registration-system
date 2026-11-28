/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.yrusprint.helpers.DataService;

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
		
		List<Map<String, Object>> registrationData = DataService.read("data/registration_entries.json", (e) -> {
			return (e.get("eventId").equals(String.valueOf(id)));
		});;

		Map<String, Integer> raceCategoryCount = new HashMap<>(); 
		for (Map<String, Object> r : registrationData) {
			if (!r.containsKey("raceCategory")) continue;
			raceCategoryCount.merge((String)r.get("raceCategory"), 1, Integer::sum);
		}
		
		for (Map<String, Object> category : (List<Map<String, Object>>) eventEntry.get("raceCategories")) {
			String raceCategoryName = (String)category.get("categoryName");
			Integer count = raceCategoryCount.get(raceCategoryName);
			this.raceCategories.add(new RaceCategory(
				raceCategoryName, 
				(Double)category.get("registrationFee"), 
				(int)category.get("minimumAge"), 
				(int)category.get("slotLimit"),
				(count == null) ? 0 : count
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

	public RaceCategory getRaceCategoryByName(String name) {
		for (RaceCategory rC : raceCategories) {
            if (rC.getCategoryName().equals(name)) {
                return rC;
            }
        }
        return null;
	}
}

package com.mycompany.yrusprint.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.yrusprint.models.Registration;

public class SearchService {
	public static List<Registration> findRegistrations(String bibIdOrContactNumber) {
		List<Map<String, Object>> registrationEntries = DataService.read("data/registration_entries.json");
		List<Registration> result = new ArrayList<>();

		for (Map<String, Object> entry : registrationEntries) {
			if (entry.get("bibId").equals(bibIdOrContactNumber) || 
			((Map<String, String>)entry.get("runner")).get("contactNumber").equals(bibIdOrContactNumber)) {
				result.add(new Registration(entry));
			}
		}

		return result;
	}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.yrusprint.models.RaceEvent ;
/**
 *
 * @author tempus
 */
public class EventManager {
    private static EventManager instance;
  
    private List<RaceEvent> events = new ArrayList<RaceEvent>();
   
    private EventManager() {}
 
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public List<RaceEvent> getEvents() {
        return events;
    }

    public void fetchEvents() {
        List<Map<String, Object>> eventsData = DataService.read("events.json");
        events = new ArrayList<>();
        if (eventsData.size() == 0) return;

        for (Map<String, Object> event : eventsData) {
            RaceEvent e = new RaceEvent(event);
            events.add(e);
        }
    }

    public void updateEvents() {

    }
}

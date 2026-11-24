/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint;
import java.util.ArrayList;
/**
 *
 * @author tempus
 */
public class EventManager {
    private static EventManager instance;
  
    private ArrayList<String> events;
   
    private EventManager() {
        events = new ArrayList<>();
    }
 
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }
 
    public void addEvent(String eventName) {
        events.add(eventName);
    }
 
    public ArrayList<String> getEvents() {
        return events;
    }
 
    public void showEvents() {
        if (events.isEmpty()) {
            System.out.println("No race events yet.");
            return;
        }
 
        System.out.println("=== Marathon Race Events ===");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i));
        }
    }
}

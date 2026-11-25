/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint;
import java.util.Map;

/**
 *
 * @author tempus
 */
public class RaceEvent {
	private String id;
	private String name;
	private String description;
	private Map<String, Integer> pricing;
	private Map<String, Integer> slots; 
	
	RaceEvent(String name, String description, Map<String, Integer> pricing, Map<String, Integer> slots) {
		this.name = name;
		this.description = description;
		this.pricing = pricing;
		this.slots = slots;
	}
}

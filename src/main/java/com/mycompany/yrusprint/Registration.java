/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint;

import java.time.LocalDate;

/**
 *
 * @author tempus
 */
public class Registration {
    Runner runner;
	LocalDate date;
	String eventId;

	Registration(Runner runner, LocalDate date, String eventId) {
		this.runner = runner;
		this.date = date;
		this.eventId = eventId;
	}
}

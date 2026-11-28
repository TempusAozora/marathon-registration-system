/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Map;

import com.mycompany.yrusprint.models.RaceCategory;
import com.mycompany.yrusprint.models.RaceEvent;
import com.mycompany.yrusprint.models.ValidationResult;

/**
 *
 * @author tempus
 */
public class AgeValidator extends Validator {
    @Override
    public ValidationResult validate(Map<String, String> entry) {

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("MM/dd/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDate date;

        try {
            date = LocalDate.parse(entry.get("birthDate"), formatter);
        } catch (DateTimeParseException e) {
            return new ValidationResult(false, "Invalid birthdate. Use MM/DD/YYYY.");
        }

        if (date.isAfter(LocalDate.now())) {
            return new ValidationResult(false, "Birthdate cannot be in the future.");
        }

        RaceEvent event = EventManager.getInstance().getEventById(Integer.parseInt(entry.get("eventId")));
        RaceCategory raceCategory = event.getRaceCategoryByName(entry.get("raceCategory"));
        int minimumAge = raceCategory.getMinimumAge();

        if (date.isAfter(LocalDate.now().minusYears(minimumAge))) {
            return new ValidationResult(false, "You must be at least " + minimumAge + " years old.");
        }

        return passToNext(entry);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

import java.util.Map;

import com.mycompany.yrusprint.models.ValidationResult;

/**
 *
 * @author tempus
 */
public class BlankValidator extends Validator {
    @Override 
    public ValidationResult validate(Map<String, String> entry) {
        for (Map.Entry<String, String> e : entry.entrySet()) {
            if (e.getKey() != "middleName" && (e.getValue() != null && e.getValue().isEmpty())) {
                return new ValidationResult(false, "One or more empty fields.");
            }
        }

        return passToNext(entry);
    }
}

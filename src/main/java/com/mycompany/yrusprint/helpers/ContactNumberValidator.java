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
public class ContactNumberValidator extends Validator {
    @Override
    public ValidationResult validate(Map<String, String> entry) {
        String contactNumber = entry.get("contactNumber");
        if (!contactNumber.matches("^09\\d{9}$")) {
            return new ValidationResult(false,
                    "Contact number must be a valid 11-digit mobile number (e.g., 09171234567).");
        }

        return passToNext(entry);
    }
}
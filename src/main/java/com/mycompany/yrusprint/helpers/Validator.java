/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

import java.util.Map;
import java.util.function.Consumer;
import com.mycompany.yrusprint.models.ValidationResult;

/**
 *
 * @author tempus
 */

public abstract class Validator {
    protected Validator next;
    protected Consumer<String> callback; 

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public abstract ValidationResult validate(Map<String, String> entry);

    protected ValidationResult passToNext(Map<String, String> entry) {
        if (next == null) {
            return new ValidationResult(true, "Success.");
        }

        return next.validate(entry);
    }
}





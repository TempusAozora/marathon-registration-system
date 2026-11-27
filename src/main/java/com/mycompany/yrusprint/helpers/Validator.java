/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.function.Consumer;

import com.mycompany.yrusprint.models.RaceCategory;
import com.mycompany.yrusprint.models.RaceEvent;
import com.mycompany.yrusprint.models.Registration;
import com.mycompany.yrusprint.models.Runner;

/**
 *
 * @author tempus
 */

abstract class Validator {
    protected Validator next;
    protected Consumer<String> callback; 

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public abstract boolean validate(Map<String, String> entry);

    protected boolean passToNext(Map<String, String> entry) {
        if (next == null) {
            return true;
        }

        return next.validate(entry);
    }
}

class FieldValidator extends Validator {
    @Override
    public boolean validate(Map<String, String> entry) {
        for (Map.Entry<String, String> e : entry.entrySet()) {
            if (e.getValue().isEmpty()) {
                callback.accept("One or more empty fields");
                return false;
            }
        }

        return passToNext(entry);
    }
}
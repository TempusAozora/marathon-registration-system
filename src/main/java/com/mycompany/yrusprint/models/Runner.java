/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author tempus
 */
public class Runner extends Person {
    private String singletSize; // from 2XS to 2XL
    
    Runner(
            PersonName name, 
            String gender,
            String contactNumber,
            LocalDate birthDate, 
            String singletSize
    ) {
        super(name, gender, birthDate, contactNumber);
        this.singletSize = singletSize;
    }

    Runner(Map<String, Object> runnerEntry) {
        super(
            new PersonName((Map<String, String>)runnerEntry.get("name")), 
            (String)runnerEntry.get("gender"),
            LocalDate.parse((String)runnerEntry.get("birthDate")),
            (String)runnerEntry.get("contactNumber")
        );
        
        this.singletSize = (String)runnerEntry.get("singletSize");
    }

    public String getSingletSize() {
        return singletSize;
    }
}

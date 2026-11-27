/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;

import java.util.Map;

/**
 *
 * @author tempus
 */

public class PersonName {
    private String lastName;
    private String firstName;
    private String middleName = "";
    
    PersonName(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
    
    PersonName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    PersonName(Map<String, String> nameEntry) {
        this.lastName = nameEntry.get("lastName");
        this.firstName = nameEntry.get("firstName");
        this.middleName = nameEntry.get("middleName");
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
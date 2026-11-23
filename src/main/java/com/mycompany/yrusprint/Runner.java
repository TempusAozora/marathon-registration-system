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
public class Runner extends Person {
    private String singletSize; // from 2XS to 2XL
    private RaceCategory raceCategory;
    
    Runner(
            PersonName name, 
            String gender,
            String contactNumber,
            LocalDate birthDate, 
            String singletSize, 
            RaceCategory raceCategory
    ) {
        super(name, gender, birthDate, contactNumber);
        this.singletSize = singletSize;
        this.raceCategory = raceCategory;
    }
}

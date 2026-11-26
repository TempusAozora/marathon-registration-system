/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;
import java.time.LocalDate;
/**
 *
 * @author tempus
 */
public class Person {
    private PersonName name;
    private String gender;
    private LocalDate birthDate;
    private String contactNumber;
    
    Person(PersonName name, String gender, LocalDate birthDate, String contactNumber) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.contactNumber = contactNumber;
    }
}
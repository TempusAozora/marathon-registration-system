/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.models;

class BibGenerator {

    private int nextBibNumber = 1001;

    public int generateBibNumber() {
        return nextBibNumber++;
    }

    public static void main(String[] args) {

        BibGenerator bib = new BibGenerator();

        System.out.println("Generated Bib Numbers:");
        System.out.println(bib.generateBibNumber());
        System.out.println(bib.generateBibNumber());
        System.out.println(bib.generateBibNumber());
    }
}
/**
 *
 * @author tempus
 */
public class RaceBib {
    static void generate(Registration registration) {
		
	}
}

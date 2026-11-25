
package com.mycompany.yrusprint;

public class RaceCategory {
    
    // 1. Attributes
    private String categoryName;
    private double registrationFee;
    private int minimumAge;
    private int slotLimit;
    private int registeredSlots;

    // 2. Constructor
    public RaceCategory(String categoryName, double registrationFee,
                        int minimumAge, int slotLimit) {

        this.categoryName = categoryName;
        this.registrationFee = registrationFee;
        this.minimumAge = minimumAge;
        this.slotLimit = slotLimit;
        this.registeredSlots = 0;
    }

    // 3. Getters
    public String getCategoryName() {
        return categoryName;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public int getSlotLimit() {
        return slotLimit;
    }

    public int getRegisteredSlots() {
        return registeredSlots;
    }

    // 4. Check if the runner meets the minimum age
    public boolean isAgeEligible(int age) {
        return age >= minimumAge;
    }

    // 5. Check if there are still available slots
    public boolean hasAvailableSlots() {
        return registeredSlots < slotLimit;
    }

    // 6. Get the remaining slots
    public int getAvailableSlots() {
        return slotLimit - registeredSlots;
    }

    // 7. Register a runner
    public boolean registerRunner(int age) {

        if (!isAgeEligible(age)) {
            return false;
        }

        if (!hasAvailableSlots()) {
            return false;
        }

        registeredSlots++;
        return true;
    }
}


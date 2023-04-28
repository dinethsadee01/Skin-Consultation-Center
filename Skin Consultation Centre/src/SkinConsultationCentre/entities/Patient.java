package SkinConsultationCentre.entities;

import java.time.LocalDate;

/**
 * This class is for Patient specified details
 */
public class Patient extends Person{
    private String pId;

    /**
     * Relevant constructor
     * @param pId Patient ID unique for patients
     */
    public Patient(String surName, String name, LocalDate dateOfBirth, String mobileNumber, String pId) {
        super(surName, name, dateOfBirth, mobileNumber);
        this.pId = pId;
    }

    /**
     * Relevant getters setters
     * @return patient ID
     */
    public String getpId() {
        return pId;
    }
}

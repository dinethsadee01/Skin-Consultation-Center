package SkinConsultationCentre.entities;

import java.time.LocalDate;

/**
 * This class is for doctor specified details
 */
public class Doctor extends Person {
    private String medicalLicenceNo;
    private String specialisation;

    /**
     * relevant constructors
     * @param medicalLicenceNo medical license number of doctor (Unique for Doctors)
     * @param specialisation specialization (e.g. cosmetic dermatology, medical dermatology, paediatric
     * dermatology, etc.)
     */
    public Doctor(String name, String surName, LocalDate dateOfBirth, String mobileNumber, String medicalLicenceNo, String specialisation) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNo = medicalLicenceNo;
        this.specialisation = specialisation;
    }

    public Doctor() {
    }

    /**
     * getters and setters
     */
    public String getMedicalLicenceNo() {
        return medicalLicenceNo;
    }

    public void setMedicalLicenceNo(String medicalLicenceNo) {
        this.medicalLicenceNo = medicalLicenceNo;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}

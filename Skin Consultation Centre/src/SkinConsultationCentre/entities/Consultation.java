package SkinConsultationCentre.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    private Patient patient;
    private String doctorMLNo;
    private LocalDate date;
    private LocalTime sTime;
    private LocalTime eTime;
    private int cost;
    private String notes;

    public Consultation(String doctorMLNo, LocalDate date, LocalTime sTime, LocalTime eTime, int cost, String notes, Patient patient) {
        this.doctorMLNo = doctorMLNo;
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.cost = cost;
        this.notes = notes;
        this.patient = patient;
    }

    public Consultation() {
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDoctorMLNo() {

        return doctorMLNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getsTime() {
        return sTime;
    }

    public LocalTime geteTime() {
        return eTime;
    }

    public int getCost() {
        return cost;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "patient=" + patient +
                ", doctorMLNo='" + doctorMLNo + '\'' +
                ", date=" + date +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }
}

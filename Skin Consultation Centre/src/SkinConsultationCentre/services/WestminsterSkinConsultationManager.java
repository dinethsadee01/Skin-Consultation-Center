package SkinConsultationCentre.services;


import SkinConsultationCentre.entities.Consultation;
import SkinConsultationCentre.entities.Doctor;
import SkinConsultationCentre.entities.Patient;
import SkinConsultationCentre.gui.MainGUI;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    /**
     * Lists of Doctor, Patient and Consultation objects
     */
    public static ArrayList<Doctor> doctorList = new ArrayList<>(10);
    public static ArrayList<Patient> patientList = new ArrayList<>();
    public static ArrayList<Consultation> consultationList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public static boolean running = true;

    /**
     * Checking existence of a Doctor
     * @param medLicence User entered Medical Licence Number
     * @return true if existed
     */
    public boolean exist(String medLicence){
        for (Doctor d : doctorList){
            if (d.getMedicalLicenceNo().equals(medLicence)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adding new Doctor to the list
     */
    public void addNewDoctor(){
        /* Max doctor objects can exist */
        int maxDoctorCount = 10;
        if(doctorList.size() <= maxDoctorCount){
            Doctor doctor = new Doctor();

            System.out.println("<-- ENTER THE FOLLOWINGS CORRECTLY -->");
            while(true){
                System.out.print("1. Surname: ");
                String vSurname = sc.nextLine();
                vSurname = vSurname.trim();
                if(!vSurname.matches("[a-zA-Z ]*")){
                    System.out.println("Invalid Surname !\nTry Again...");
                } else {
                    doctor.setSurName(vSurname.toUpperCase());
                    break;
                }
            }

            while(true){
                System.out.print("2. Name: ");
                String vName = sc.nextLine();
                vName = vName.trim();
                if(!vName.matches("[a-zA-Z ]*")){
                    System.out.println("Invalid Name !\nTry Again...");
                } else {
                    doctor.setName(vName.toUpperCase());
                    break;
                }
            }

            while(true){
                System.out.print("3. DOB (YYYY,MM,DD): ");
                try {
                    String[] YMD = sc.nextLine().split(",");
                    int year = Integer.parseInt(YMD[0]);
                    int month = Integer.parseInt(YMD[1]);
                    int date = Integer.parseInt(YMD[2]);
                    if(LocalDate.now().isAfter(LocalDate.of(year, month, date))){
                        doctor.setDateOfBirth(LocalDate.of(year, month, date));
                        break;
                    } else{
                        System.out.println("Invalid Birthday !\nTry Again...");
                    }
                }catch (Exception e){
                    System.out.println("Invalid Birthday !\nTry Again...\n---(Enter as YYYY,MM,DD)---");
                }
            }

            while(true){
                System.out.print("4. Mobile Number: ");
                String vMobile = sc.nextLine();
                if(!vMobile.matches("^0[1-9]{9}$")){
                    System.out.println("Invalid Mobile Number !\nTry Again...\n---(Enter as 0XXXXXXXXX)---");
                } else {
                    doctor.setMobileNumber(vMobile);
                    break;
                }
            }

            while(true){
                System.out.print("5. Medical Licence No: ");
                String vMedLicence = sc.nextLine();
                if(!vMedLicence.matches("[0-9]*")){
                    System.out.println("Invalid Medical Licence !\nTry Again...");
                } else if(exist(vMedLicence)){
                    System.out.println("THIS MEDICAL REGISTRATION NUMBER ALREADY EXISTED !");
                } else {
                    doctor.setMedicalLicenceNo(vMedLicence);
                    break;
                }
            }

            while(true) {
                System.out.print("6. Specialisation: ");
                String vSpecialisation = sc.nextLine();
                vSpecialisation = vSpecialisation.trim();
                if(!vSpecialisation.matches("[a-zA-Z ]*")){
                    System.out.println("Invalid Specialization !\nTry Again...");
                } else {
                    doctor.setSpecialisation(vSpecialisation.toUpperCase());
                    break;
                }
            }

            //Adding object to the list
            doctorList.add(doctor);
            System.out.println("DOCTOR "+doctor.getName()+" ADDED SUCCESSFULLY");
        }else{
            System.out.println("10 DOCTORS ARE ALREADY IN THE SYSTEM\nDELETE SOMEONE TO ADD MORE\n");
        }
    }

    /**
     * Deleting existing doctor
     */
    public void deleteDoctor(){
        if(doctorList.isEmpty()) {
            System.out.println("NO DOCTOR TO REMOVE, LIST IS EMPTY");
        }else {
            boolean iteration = true;
            while (iteration) {
                System.out.println("ENTER THE MEDICAL LICENCE NUMBER OF THE DOCTOR YOU WANT TO REMOVE: ");
                String mln = sc.next();
                if (!mln.matches("[0-9]*")) {
                    System.out.println("INVALID MEDICAL LICENCE !\nTry Again...");
                } else {
                    for (Doctor str : doctorList) {
                        if (mln.equals(str.getMedicalLicenceNo())) {
                            System.out.printf("%n %2s  %-30s  %-30s  %-18s  %-20s  %-20s  %-13s %n", " ", "| Surname", "| Name", "| D.O.B", "| Mobile No", "| M.L.No", "| Specialisation");
                            System.out.printf("%n %2s  %-30s  %-30s  %-18s  %-20s  %-20s  %-13s %n", "-> ",
                                    str.getSurName(),
                                    str.getName(),
                                    str.getDateOfBirth(),
                                    str.getMobileNumber(),
                                    str.getMedicalLicenceNo(),
                                    str.getSpecialisation());
                            System.out.println("\nREMOVED DOCTOR UNDER " + mln);
                            doctorList.remove(str);
                            System.out.println("\n"+doctorList.size()+" DOCTORS REMAINING WITH US ✅");
                            iteration = false;
                            break;
                        }
                    }
                    if(iteration) System.out.println("NO DOCTOR REGISTERED UNDER " + mln);
                }
            }
        }
    }

    /**
     * Get a list of existing doctors
     */
    public void printDoctorList(){
        if(doctorList.isEmpty()){
            System.out.println("THERE ARE NO DOCTORS IN THE SYSTEM");
        }else{
            System.out.printf("%n %2s  %-30s  %-30s  %-18s  %-20s  %-20s  %-13s %n", " ", "| Surname", "| Name", "| D.O.B", "| Mobile No", "| M.L.No", "| Specialisation");
            doctorList.sort((doc1, doc2) ->
            {
                String s1 = doc1.getSurName();
                String s2 = doc2.getSurName();
                return s1.compareTo(s2);
            });
            int no = 1;
            for (Doctor str : doctorList) {
                System.out.printf("%n %2s  %-30s  %-30s  %-18s  %-20s  %-20s  %-13s %n",(no) + ") ",
                        str.getSurName(),
                        str.getName(),
                        str.getDateOfBirth(),
                        str.getMobileNumber(),
                        str.getMedicalLicenceNo(),
                        str.getSpecialisation());
                no++;
            }
        }
    }

    /**
     * Save ArrayList of objects to a txt file
     */
    public void saveDoctorData(){
        final String filePath = "Doctor.txt";
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(doctorList);
            oos.close();
            fos.close();
            System.out.println("DOCTORS ADDED TO THE SYSTEM BACKUP");
        } catch (Exception e) {
            System.out.print("ERROR SAVING BACKUP!");
        }
    }

    /**
     * Load data from txt file from previous saved attempt
     */
    public void loadDoctorData(){
        try {
            FileInputStream fis1 = new FileInputStream("Doctor.txt");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            doctorList = (ArrayList<Doctor>) ois1.readObject();
            ois1.close();
            fis1.close();
            System.out.println("DOCTORS LOADED FROM THE SYSTEM BACKUP");
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("ERROR LOADING BACKUPS!\n STARTING A NEW ATTEMPT.....");
        }
    }

    public void consoleMenu(){
        System.out.println("""                 
                                     
                                     =======================================================
                                     |                                                     |
                                     |               -------WELCOME-------                 |
                                     |             \033[3mSKIN CONSULTATION CENTRE\033[0m                |
                                     |       :._________________________________.:         |
                                     :                                                     :
                                     ===========SELECT=AND=ENTER=OPERATION=NUMBER===========
                                     |                                                     |
                                     |      1 : ADD NEW DOCTOR                             |
                                     |      2 : DELETE EXISTING DOCTOR                     |
                                     |      3 : VIEW ALL DOCTORS                           |
                                     |      4 : SAVE DATA TO FILE                          |
                                     |      5 : VIEW USER INTERFACE                        |
                                     |      6 : EXIT THE APPLICATION                       |
                                     |                                                     |
                                     =======================================================
                                     
                    """);
        System.out.println("Enter the NUMBER related to the action you want to perform : ");
    }

    public void defaultMenuAction() {
        System.out.print("OPERATION DOS NOT EXIST! PLEASE ENTER VALID INPUT.\n ");
    }  //if inputted menu operation invalid

    public void exitMenu() {
        System.out.print("""
                                 =======================================================
                                 | THANK YOU FOR BEING WITH US ------------ COME AGAIN |
                                 =======================================================
                """);
        running = false; //stopping main menu looping
    }

    public void gui(){
        System.out.println("""
        ===========================================
        |    STARTING GRAPHICAL USER INTERFACE    |
        ===========================================
        """);
        MainGUI mgui = new MainGUI();
        running = false;
    }
}
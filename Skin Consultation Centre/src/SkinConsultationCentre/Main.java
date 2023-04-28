package SkinConsultationCentre;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;

import java.util.Scanner;

import static SkinConsultationCentre.gui.AddConsultationGUI.loadConsultationData;
import static SkinConsultationCentre.gui.AddConsultationGUI.loadPatientData;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    static WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    public static void mainLoop(){
        while(WestminsterSkinConsultationManager.running){
            manager.consoleMenu();
            String menuAction = sc.nextLine();

            //Iteration for show menu after every action
            switch (menuAction) {
                case "1" -> manager.addNewDoctor();
                case "2" -> manager.deleteDoctor();
                case "3" -> manager.printDoctorList();
                case "4" -> manager.saveDoctorData();
                case "5" -> manager.gui();
                case "6" -> manager.exitMenu();
                default -> manager.defaultMenuAction();
            }
        }
    }

    public static void main(String[] args) {
        while(WestminsterSkinConsultationManager.running){
            System.out.print("""
                DO YOU WANT TO,
                * RESTORE DATA FROM LATEST BACKUP - ENTER 1
                * TO START NEW ATTEMPT            - ENTER 2
                """);

            switch (sc.nextLine()) {
                case "1" -> {
                    manager.loadDoctorData();
                    loadConsultationData();
                    loadPatientData();
                    mainLoop();
                }
                case "2" -> mainLoop();
                default -> manager.defaultMenuAction();
            }
        }
    }
}
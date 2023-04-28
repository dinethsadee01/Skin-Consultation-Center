package SkinConsultationCentre.gui;

import SkinConsultationCentre.entities.Consultation;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ConsultationsGUI extends JFrame {
    ArrayList<Consultation> consultationListGUI = WestminsterSkinConsultationManager.consultationList;

    public ConsultationsGUI(){
        setTitle("Existing Consultations");
        setVisible(true);
        setSize(1500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] tableColumns = {"Patient ID", "Doctor", "Date", "Time", "Cost", "Notes"};
        Object[][] data = new Object[consultationListGUI.size()][6];
        int index = 0;
        for (Consultation consultation : consultationListGUI) {
            data[index][0] = consultation.getPatient().getpId();
            data[index][1] = consultation.getDoctorMLNo();
            data[index][2] = consultation.getDate();
            data[index][3] = consultation.getsTime()+"-"+consultation.geteTime();
            data[index][4] = consultation.getCost();
            data[index][5] = AddConsultationGUI.decrypt(consultation.getNotes(),AddConsultationGUI.secretKey1);
            index++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumns);
        JTable consultationTable = new JTable(tableModel);
        consultationTable.setAutoCreateRowSorter(true);
        consultationTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(consultationTable);

        add(scrollPane);
        addWindowListener(new ConsultationsGUI.windowHandler());
    }

    private class windowHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            MainGUI mgui = new MainGUI();
            dispose();
        }
    }
}

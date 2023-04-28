package SkinConsultationCentre.gui;

import SkinConsultationCentre.entities.Doctor;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DoctorsGUI extends JFrame {
    ArrayList<Doctor> doctorListGUI = WestminsterSkinConsultationManager.doctorList;

    public DoctorsGUI(){
        setTitle("Doctors With Us");
        setVisible(true);
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Surname", "Name", "DOB", "Mobile No", "M.L. No", "Specialisation"};
        Object[][] data = new Object[doctorListGUI.size()][6];
        int index = 0;
        for (Doctor doctor : doctorListGUI) {
            data[index][0] = doctor.getSurName();
            data[index][1] = doctor.getName();
            data[index][2] = doctor.getDateOfBirth();
            data[index][3] = doctor.getMobileNumber();
            data[index][4] = doctor.getMedicalLicenceNo();
            data[index][5] = doctor.getSpecialisation();
            index++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable doctorTable = new JTable(tableModel);
        doctorTable.setAutoCreateRowSorter(true);
        doctorTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(doctorTable);

        add(scrollPane);
        addWindowListener(new windowHandler());
    }
    private class windowHandler extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            MainGUI mgui = new MainGUI();
            dispose();
        }
    }
}

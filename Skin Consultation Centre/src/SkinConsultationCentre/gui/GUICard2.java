package SkinConsultationCentre.gui;

import SkinConsultationCentre.entities.Consultation;
import SkinConsultationCentre.entities.Patient;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.temporal.ChronoUnit;

public class GUICard2 extends JPanel {
    private JLabel title;
    private JLabel pSurnameL;
    private JTextField pSurnameT;
    private JLabel pNameL;
    private JTextField pNameT;
    private JLabel pMobileNoL;
    private JTextField pMobileNoT;
    private JLabel pIDL;
    private JTextField pIDT;
    private JLabel pDOBL;
    private DatePicker pDOBP;
    private JLabel pNoteL;
    private JTextArea pNoteT;
    private JButton pClearB;
    private JButton pBackB;
    private JButton pNextB;
    static int cost;

    public GUICard2() {
        setLayout (null);

        title = new JLabel ("-- Add Patient --");
        add (title);
        title.setBounds (30, 0, 140, 60);

        pSurnameL = new JLabel ("Surname");
        add (pSurnameL);
        pSurnameL.setBounds (35, 60, 100, 25);

        pSurnameT = new JTextField (30);
        add (pSurnameT);
        pSurnameT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if(pSurnameT.getText().trim().matches("[a-zA-Z ]*")){
                    pSurnameT.setBackground(Color.decode("#80e89a"));
                } else {
                    pSurnameT.setBackground(Color.decode("#e88080"));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        pSurnameT.setBounds (35, 90, 280, 25);

        pNameL = new JLabel ("Name");
        add (pNameL);
        pNameL.setBounds (35, 125, 100, 25);

        pNameT = new JTextField (30);
        add (pNameT);
        pNameT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if(pNameT.getText().trim().matches("[a-zA-Z ]*")){
                    pNameT.setBackground(Color.decode("#80e89a"));
                } else {
                    pNameT.setBackground(Color.decode("#e88080"));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        pNameT.setBounds (35, 155, 280, 25);

        pDOBL = new JLabel ("Date of birth");
        add (pDOBL);
        pDOBL.setBounds (35, 190, 100, 25);

        DatePickerSettings set = new DatePickerSettings();
        set.setAllowKeyboardEditing(false);
        pDOBP = new DatePicker(set);
        add (pDOBP);
        pDOBP.setBounds (35, 220, 170, 25);

        pMobileNoL = new JLabel ("Mobile Number");
        add (pMobileNoL);
        pMobileNoL.setBounds (35, 255, 100, 25);

        pMobileNoT = new JTextField (10);
        add (pMobileNoT);
        pMobileNoT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if(pMobileNoT.getText().trim().matches("^0[1-9]{9}$")){
                    pMobileNoT.setBackground(Color.decode("#80e89a"));
                } else {
                    pMobileNoT.setBackground(Color.decode("#e88080"));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        pMobileNoT.setBounds (35, 285, 155, 25);

        pIDL = new JLabel ("Patient's NIC");
        add (pIDL);
        pIDL.setBounds (35, 320, 100, 25);

        pIDT = new JTextField (3);
        add (pIDT);
        pIDT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if(pIDT.getText().trim().matches("[0-9]{12}$") || pIDT.getText().trim().matches("[0-9]{9}[Vv]$")){
                    pIDT.setBackground(Color.decode("#80e89a"));
                } else {
                    pIDT.setBackground(Color.decode("#e88080"));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        pIDT.setBounds (35, 350, 200, 25);

        pNoteL = new JLabel("Note");
        add (pNoteL);
        pNoteL.setBounds(35,385,100,25);

        pNoteT = new JTextArea (30, 30);
        add (pNoteT);
        pNoteT.setBounds(35,415,270,120);

        pClearB = new JButton("Reset");
        add (pClearB);
        pClearB.setBounds(35,570,100,25);
        pClearB.addActionListener(actionEvent -> {
            pSurnameT.setText(null);
            pSurnameT.setBackground(Color.white);
            pNameT.setText(null);
            pNameT.setBackground(Color.white);
            pMobileNoT.setText(null);
            pMobileNoT.setBackground(Color.white);
            pIDT.setText(null);
            pIDT.setBackground(Color.white);
            pDOBP.setDate(null);
            pNoteT.setText(null);
        });

        pBackB = new JButton("Back");
        add (pBackB);
        pBackB.setBounds(140,570,100,25);
        pBackB.addActionListener(actionEvent -> AddConsultationGUI.cl.previous(AddConsultationGUI.jp));

        pNextB = new JButton("Next");
        add (pNextB);
        pNextB.setBounds(245,570,100,25);
        pNextB.addActionListener(actionEvent -> {
            if(pSurnameT.getBackground().equals(Color.decode("#e88080")) || pSurnameT.getText().isBlank()
                    || pNameT.getBackground().equals(Color.decode("#e88080")) || pNameT.getText().isBlank()
                    || pMobileNoT.getBackground().equals(Color.decode("#e88080")) || pMobileNoT.getText().isBlank()
                    || pIDT.getBackground().equals(Color.decode("#e88080")) || pIDT.getText().isBlank()
                    || pDOBP.getDate() == null) {
                JOptionPane.showMessageDialog(null, "CHECK INPUT FIELDS AGAIN !");
            }else{
                Patient patient = new Patient(pSurnameT.getText().toUpperCase(), pNameT.getText().toUpperCase(), pDOBP.getDate(), pMobileNoT.getText(), pIDT.getText());
                int appointDuration = (int) GUICard1.appointStartTime.until(GUICard1.appointEndTime, ChronoUnit.HOURS);
                if(checkingPatient(patient)){
                    cost = appointDuration*25;
                }else{
                    WestminsterSkinConsultationManager.patientList.add(patient);
                    cost = appointDuration*15;
                }
                Consultation consultation = new Consultation(GUICard1.availableDocMLNo,GUICard1.appointDate,GUICard1.appointStartTime,GUICard1.appointEndTime,cost,AddConsultationGUI.encrypt(pNoteT.getText(),AddConsultationGUI.secretKey1),patient);
                //System.out.println(consultation.getNotes());
                WestminsterSkinConsultationManager.consultationList.add(consultation);
                JOptionPane.showMessageDialog(null, "Date: "+
                        GUICard1.appointDate.toString()+"\nTime: "+
                        GUICard1.appointStartTime.toString()+"-"+GUICard1.appointEndTime.toString()+"\nDoctor: "+
                        GUICard1.availableDocMLNo+"\nCost: "+
                        "£"+ GUICard2.cost);
                AddConsultationGUI.cl.next(AddConsultationGUI.jp);
            }
        });
    }

    public boolean checkingPatient(Patient patient1){
        for (Patient p : WestminsterSkinConsultationManager.patientList) {
            if (patient1.getpId().equals(p.getpId())) return true;
        }
        return false;
    }
}
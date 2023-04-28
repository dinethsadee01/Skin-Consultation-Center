package SkinConsultationCentre.gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private JLabel welcomeNote;
    private JButton docList;
    private JButton addConsult;
    private JButton consultList;

    public MainGUI() {
        setTitle("Westminster Skin Clinic");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();

        /*
        Adding Image to the jFrame
        Resource : https://www.codespeedy.com/how-to-add-an-image-in-jframe/
        */
        welcomeNote = new JLabel();
        welcomeNote.setIcon(new ImageIcon("Background.png"));

        docList = new JButton("View Doctors Available");
        docList.addActionListener(actionEvent -> {
            DoctorsGUI gui = new DoctorsGUI();
            dispose();
        });
        addConsult = new JButton("Add New Consultation");
        addConsult.addActionListener(actionEvent -> {
            AddConsultationGUI acgui = new AddConsultationGUI();
            dispose();
        });
        consultList = new JButton("View Consultations Booked");
        consultList.addActionListener(actionEvent -> {
            ConsultationsGUI cgui = new ConsultationsGUI();
            dispose();
        });

        jp1.setLayout(new GridBagLayout());
        jp2.setLayout(new GridLayout(1, 3));
        jp1.add(welcomeNote);
        jp2.add(docList);
        jp2.add(addConsult);
        jp2.add(consultList);

        setLayout(new GridLayout(2, 1));
        add(jp1);
        add(jp2);

        setVisible(true);
    }
}
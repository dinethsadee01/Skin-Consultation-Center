package SkinConsultationCentre.gui;

import javax.swing.*;
import java.awt.*;

public class GUICard3 extends JPanel {
    private JLabel cTick;
    private JLabel cText;

    GUICard3(){
        setLayout(null);

        cTick = new JLabel("✅");
        add(cTick);
        cTick.setBounds(165,200,250,100);
        cTick.setFont(new Font("",Font.BOLD,60));

        cText = new JLabel ("Successfully Booked !");
        add(cText);
        cText.setBounds(90,300,250,100);
        cText.setFont(new Font("", Font.PLAIN, 20));
    }
}

package SkinConsultationCentre.gui;

import SkinConsultationCentre.entities.Consultation;
import SkinConsultationCentre.entities.Doctor;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;

//Resource : https://github.com/LGoodDatePicker/LGoodDatePicker/releases/download/v11.2.1-Standard/LGoodDatePicker-11.2.1.jar
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GUICard1 extends JPanel {
    private JLabel title;
    private JLabel daDateL;
    private DatePicker daDateP;
    private JLabel daSTimeL;
    private TimePicker daSTimeP;
    private JLabel daETimeL;
    private TimePicker daETimeP;
    private JLabel daDoctL;
    private JComboBox daDoctComb;
    private JButton daCheckB;
    private JButton daClearB;
    private JButton daNextB;
    private boolean available;
    private String appointDoctor;
    private int docIndex;
    static String availableDocMLNo;
    static LocalDate appointDate;
    static LocalTime appointStartTime;
    static LocalTime appointEndTime;

    public GUICard1() {
        setLayout (null);

        title = new JLabel ("-- Check Doctor Availability --");
        add (title);
        title.setBounds (10, 10, 170, 25);

        daDateL = new JLabel ("Pick Date");
        add (daDateL);
        daDateL.setBounds (10, 45, 100, 25);

        DatePickerSettings dSet = new DatePickerSettings();
        dSet.setAllowKeyboardEditing(false);
        daDateP = new DatePicker(dSet);
        add (daDateP);
        daDateP.setBounds(10,75,170,25);

        daSTimeL = new JLabel ("Pick Start Time");
        add (daSTimeL);
        daSTimeL.setBounds (10, 110, 100, 25);

        TimePickerSettings tSet1 = new TimePickerSettings();
        tSet1.use24HourClockFormat();
        tSet1.setAllowKeyboardEditing(false);
        tSet1.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.OneHour, LocalTime.parse("09:00"),LocalTime.parse("15:00"));
        daSTimeP = new TimePicker(tSet1);
        add (daSTimeP);
        daSTimeP.setBounds (10, 140, 100, 25);

        daETimeL = new JLabel ("Pick End Time");
        add (daETimeL);
        daETimeL.setBounds (160, 110, 100, 25);

        TimePickerSettings tSet2 = new TimePickerSettings();
        tSet2.use24HourClockFormat();
        tSet2.setAllowKeyboardEditing(false);
        tSet2.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.OneHour, LocalTime.parse("10:00"),LocalTime.parse("16:00"));
        daETimeP = new TimePicker(tSet2);
        add (daETimeP);
        daETimeP.setBounds (160, 140, 100, 25);

        daDoctL = new JLabel ("Pick a Doctor");
        add (daDoctL);
        daDoctL.setBounds (10, 175, 100, 25);

        ArrayList<String> doctorName = new ArrayList<>();
        for(Doctor d : WestminsterSkinConsultationManager.doctorList){
            doctorName.add(d.getMedicalLicenceNo()+" "+d.getName());
        }
        daDoctComb = new JComboBox (doctorName.toArray());
        add (daDoctComb);
        daDoctComb.setBounds (10, 205, 200, 25);

        daCheckB = new JButton ("Check");
        add (daCheckB);
        daCheckB.setBounds (10, 250, 95, 30);
        daCheckB.addActionListener(actionEvent -> {
            appointDate = daDateP.getDate();
            appointStartTime = daSTimeP.getTime();
            appointEndTime = daETimeP.getTime();
            appointDoctor = daDoctComb.getSelectedItem().toString().split(" ")[0];
            if (appointDate==null || appointStartTime ==null || appointEndTime ==null){
                JOptionPane.showMessageDialog(null, "SELECT DATE AND TIMESLOT !");
            } else if(appointEndTime.isBefore(appointStartTime)||appointEndTime.equals(appointStartTime)) {
                JOptionPane.showMessageDialog(null, "PLEASE ENTER VALID TIMESLOT !");
            } else if(appointDate.isBefore(LocalDate.now())){
                JOptionPane.showMessageDialog(null, "PLEASE ENTER VALID DATE !");
            } else if(appointStartTime.isBefore(LocalTime.parse("08:00"))){
                JOptionPane.showMessageDialog(null, "PLEASE SELECT UPCOMING START TIME !");
            } else{
                if(!isAvailable(appointDate,appointStartTime,appointEndTime,appointDoctor)){
                    JOptionPane.showMessageDialog(null, "DOCTOR UNAVAILABLE, SEARCHING AVAILABILITY OF ANOTHER");
                    checkingDoctor(appointDate,appointStartTime,appointEndTime);
                    if(available){
                        JOptionPane.showMessageDialog(null, "DOCTOR AVAILABLE");
                        daDoctComb.setSelectedIndex(docIndex);
                        daNextB.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "DOCTORS UNAVAILABLE");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "DOCTOR AVAILABLE");
                    availableDocMLNo = appointDoctor;
                    daNextB.setEnabled(true);
                }
            }
        });

        daClearB = new JButton ("Reset");
        add (daClearB);
        daClearB.setBounds (110, 250, 90, 30);
        daClearB.addActionListener(actionEvent -> {
            daDateP.setDate(null);
            daSTimeP.setTime(null);
            daETimeP.setTime(null);
            daDoctComb.setSelectedIndex(0);
            daNextB.setEnabled(false);
        });

        daNextB = new JButton ("Next");
        add (daNextB);
        daNextB.setEnabled(false);
        daNextB.setBounds (205, 250, 95, 30);
        daNextB.addActionListener(actionEvent -> AddConsultationGUI.cl.next(AddConsultationGUI.jp));
    }

    public void checkingDoctor(LocalDate appointDate, LocalTime appointStartTime, LocalTime appointEndTime){
        available = false;
        for(int i=0; i<WestminsterSkinConsultationManager.doctorList.size(); i++){
            availableDocMLNo = WestminsterSkinConsultationManager.doctorList.get(i).getMedicalLicenceNo();
            if(isAvailable(appointDate,appointStartTime,appointEndTime,availableDocMLNo)){
                docIndex = i;
                available = true;
                break;
            }
        }
    }

    public boolean isAvailable(LocalDate appointDate, LocalTime appointStartTime, LocalTime appointEndTime, String appointDoctor){
        for(Consultation consultation : WestminsterSkinConsultationManager.consultationList){
            if(
                    (appointDoctor.equals(consultation.getDoctorMLNo())) &&
                            (appointDate.equals(consultation.getDate())) &&
                            !((appointEndTime.isBefore(consultation.getsTime())||appointEndTime.equals(consultation.getsTime())) || (appointStartTime.isAfter(consultation.geteTime())||appointStartTime.equals(consultation.geteTime())))

            )
            {
                return false;
            }
        }
        return true;
    }
}
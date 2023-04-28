package SkinConsultationCentre.gui;

import SkinConsultationCentre.entities.Consultation;
import SkinConsultationCentre.entities.Patient;
import SkinConsultationCentre.services.WestminsterSkinConsultationManager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class AddConsultationGUI extends JFrame {
    public static JPanel jp;
    public static CardLayout cl;
    static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    final static String secretKey1 = "20000201"; //hard coded encrypting and decrypting key
    public AddConsultationGUI(){
        setTitle("Add New Consultation");
        setVisible(true);
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        jp = new JPanel();
        cl = new CardLayout();
        jp.setLayout(cl);

        jp.add(new GUICard1());
        jp.add(new GUICard2());
        jp.add(new GUICard3());
        add(jp);

        addWindowListener(new AddConsultationGUI.windowHandler());
    }

    private void saveConsultationData(){
        final String filePath2 = "Consultation.txt";
        try {
            FileOutputStream fos2 = new FileOutputStream(filePath2);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(WestminsterSkinConsultationManager.consultationList);
            oos2.close();
            fos2.close();
            System.out.println("CONSULTATIONS ADDED TO THE SYSTEM BACKUP");
        } catch (Exception e) {
            System.out.print("ERROR SAVING BACKUP!");
        }
    }

    private void savePatientData(){
        final String filePath3 = "Patient.txt";
        try {
            FileOutputStream fos3 = new FileOutputStream(filePath3);
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(WestminsterSkinConsultationManager.patientList);
            oos3.close();
            fos3.close();
            System.out.println("PATIENTS ADDED TO THE SYSTEM BACKUP");
        } catch (Exception e) {
            System.out.print("ERROR SAVING BACKUP!");
        }
    }

    public static void loadConsultationData(){
        try {
            FileInputStream fis2 = new FileInputStream("Consultation.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            WestminsterSkinConsultationManager.consultationList = (ArrayList<Consultation>) ois2.readObject();
            ois2.close();
            fis2.close();
            System.out.println("CONSULTATIONS LOADED FROM THE SYSTEM BACKUP");
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("ERROR LOADING BACKUPS!\n STARTING A NEW ATTEMPT.....");
        }
    }

    public static void loadPatientData(){
        try {
            FileInputStream fis3 = new FileInputStream("Patient.txt");
            ObjectInputStream ois3= new ObjectInputStream(fis3);
            WestminsterSkinConsultationManager.patientList = (ArrayList<Patient>) ois3.readObject();
            ois3.close();
            fis3.close();
            System.out.println("PATIENTS LOADED FROM THE SYSTEM BACKUP");
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("ERROR LOADING BACKUPS!\n STARTING A NEW ATTEMPT.....");
        }
    }

    /**
     * Note encrypting and decrypting algorithm creator
     * @param myKey encryption key
     * Recourse : <a href="https://www.javaguides.net/2020/02/java-string-encryption-decryption-example.html">...</a>
     */
    public static void prepareSecreteKey(String myKey) {
        MessageDigest sha;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * encryption
     * @param strToEncrypt Note from user
     * @param secret encrypting key
     * @return encrypted string
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return null;
    }

    /**
     * decrypting
     * @param strToDecrypt decrypted string
     * @param secret decrypting key
     * @return decrypted string
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return null;
    }

    private class windowHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            saveConsultationData();
            savePatientData();
            MainGUI mgui = new MainGUI();
            dispose();
        }
    }

}

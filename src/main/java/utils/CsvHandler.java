package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Patient;
import models.User;

public class CsvHandler {
    private static final String usersHeader = "uid,name,email,passwordHash,isPatient\n";
    private static final String patientsHeader = "name,nid,gender,address,age,contactNumber\n";
    private static final String usersFileName = "users.csv";
    private static final String patientsFileName = "patients.csv";

    public CsvHandler() {
    }

    public void addUser(User user) {
        FileWriter fileWriter = null;

        try {
            File userFile = new File(usersFileName);
            fileWriter = new FileWriter(userFile);
            if (!userFile.isFile())
                fileWriter.append(usersHeader);
            fileWriter.append(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPatient(Patient patient) {
        FileWriter fileWriter = null;

        try {
            File patientFile = new File(patientsFileName);
            fileWriter = new FileWriter(patientFile);
            if (!patientFile.isFile())
                fileWriter.append(patientsHeader);
            fileWriter.append(patient.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getPatient(String nid) {
        try (BufferedReader bReader = new BufferedReader(new FileReader(patientsFileName))) {
            String line;

            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import models.Patient;
import models.User;

public class CsvHandler {
    public CsvHandler() {
    }

    public void addUser(User user) {
        String header = "uid,name,email,passwordHash,isPatient\n";
        String fileName = "users.csv";
        FileWriter fileWriter = null;

        try {
            File userFile = new File(fileName);
            fileWriter = new FileWriter(userFile);
            fileWriter.append(header);
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
        String header = "name,nid,gender,address,age,contactNumber\n";
        String fileName = "patients.csv";
        FileWriter fileWriter = null;

        try {
            File userFile = new File(fileName);
            fileWriter = new FileWriter(userFile);
            fileWriter.append(header);
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
}

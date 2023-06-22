package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Patient;
import models.User;

public class CsvHandler {
    private static final String usersHeader = "uid,name,email,passwordHash,isPatient\n";
    private static final String patientsHeader = "name,nid,gender,address,age,contactNumber,allergies\n";
    private static final String usersFileName = "users.csv";
    private static final String patientsFileName = "patients.csv";

    public CsvHandler() {
    }

    public void addUser(User user) {
        FileWriter fileWriter = null;

        try {
            boolean fileExists = new File(usersFileName).exists();
            fileWriter = new FileWriter(usersFileName, true);
            if (!fileExists)
                fileWriter.append(usersHeader);
            fileWriter.append(user.toCsvString());
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
            boolean fileExists = new File(patientsFileName).exists();
            fileWriter = new FileWriter(patientsFileName, true);
            if (!fileExists)
                fileWriter.append(patientsHeader);
            fileWriter.append(patient.toCsvString());
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

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader(patientsFileName));
            String line;
            boolean headerSkipped = false;

            while ((line = bReader.readLine()) != null) {
                if (headerSkipped) {
                    String[] info = parseCsvLine(line);
                    String name = info[0];
                    String nid = info[1];
                    String gender = info[2];
                    String address = info[3];
                    int age = Integer.parseInt(info[4]);
                    String contactNumber = info[5];
                    ArrayList<String> allergies = new ArrayList<String>(Arrays.asList(info[6].split(",")));
                    Patient patient = new Patient(name, nid, gender, address, contactNumber, age, allergies);
                    patients.add(patient);
                } else {
                    headerSkipped = true;
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return patients;
    }

    private static String[] parseCsvLine(String line) {
        ArrayList<String> fields = new ArrayList<String>();
        StringBuilder currentField = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                fields.add(currentField.toString().trim());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString().trim());

        return fields.toArray(new String[0]);
    }
}

package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import models.AppointmentRequest;
import models.Patient;
import models.User;

public class CsvHandler {
    public CsvHandler() {
    }

    // Wrapper methods

    public void addUser(User user) {
        addOneCsv(user.getFileName(), user.toCsvHeader(), user.toCsvString());
    }

    public void addPatient(Patient patient) {
        addOneCsv(patient.getFileName(), patient.toCsvHeader(), patient.toCsvString());
    }

    public void addAppointmentReq(AppointmentRequest appointmentReq) {
        addOneCsv(appointmentReq.getFileName(), appointmentReq.toCsvHeader(),
                appointmentReq.toCsvString());
    }

    public Patient getPatient(String patientNid) {
        List<Patient> patients = getCsv(new Patient().getFileName(), info -> {
            String nid = info[0];
            String name = info[1];
            String gender = info[2];
            String address = info[3];
            String nationality = info[4];
            String email = info[5];
            String contactNumber = info[6];
            int age = Integer.parseInt(info[7]);
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(info[8].split(",")));
            return new Patient(nid, name, gender, address, nationality, email, contactNumber, age, allergies);
        }, (Patient patient) -> patient.getNid().equals(patientNid), false);

        if (patients.isEmpty()) {
            return null;
        }

        return patients.get(0);
    }

    public List<Patient> getAllPatients() {
        return getCsv(new Patient().getFileName(), info -> {
            String nid = info[0];
            String name = info[1];
            String gender = info[2];
            String address = info[3];
            String nationality = info[4];
            String email = info[5];
            String contactNumber = info[6];
            int age = Integer.parseInt(info[7]);
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(info[8].split(",")));
            return new Patient(nid, name, gender, address, nationality, email, contactNumber, age, allergies);
        }, (patient) -> true, true);
    }

    public List<User> getAllUsers() {
        return getCsv(new User().getFileName(), info -> {
            String nid = info[0];
            String name = info[1];
            String email = info[2];
            String password = info[3];
            boolean isPatient = Boolean.parseBoolean(info[4]);
            return new User(nid, name, email, password, isPatient);
        }, user -> true, true);
    }

    public void updateUser(User user) {

    }

    public void updatePatient(Patient patient) {

    }

    // Implementation methods

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

    private static void addOneCsv(String fileName, String header, String data) {
        FileWriter fileWriter = null;

        try {
            boolean fileExists = new File(fileName).exists();
            fileWriter = new FileWriter(fileName, true);
            if (!fileExists)
                fileWriter.append(header);
            fileWriter.append(data);
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

    private static <T> List<T> getCsv(String fileName, Function<String[], T> createObjectFunc, Predicate<T> filter,
            boolean getAll) {
        List<T> objects = new ArrayList<>();
        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean headerSkipped = false;

            while ((line = bReader.readLine()) != null) {
                if (headerSkipped) {
                    String[] info = parseCsvLine(line);
                    T object = createObjectFunc.apply(info);
                    if (filter.test(object)) {
                        objects.add(object);
                        if (!getAll) {
                            break;
                        }
                    }
                } else {
                    headerSkipped = true;
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

        return objects;
    }

    private static <T> void updateCsv(String fileName, Predicate<T> filter, Function<String[], T> createObjectFunc,
            Function<T, String> toCsvStringFunc) {
        List<T> objects = new ArrayList<>();
        BufferedReader bReader = null;
        FileWriter fileWriter = null;

        try {
            bReader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean headerSkipped = false;

            while ((line = bReader.readLine()) != null) {
                if (headerSkipped) {
                    String[] info = parseCsvLine(line);
                    T object = createObjectFunc.apply(info);
                    objects.add(object);
                } else {
                    headerSkipped = true;
                }
            }

            for (T object : objects) {
                if (filter.test(object)) {
                    // Perform the necessary updates on the object
                    // ...

                    break;
                }
            }

            fileWriter = new FileWriter(fileName);
            fileWriter.write("header line");

            for (T object : objects) {
                fileWriter.write(System.lineSeparator());
                fileWriter.write(toCsvStringFunc.apply(object));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import models.BaseAnalysis;
import models.BaseModel;
import models.Diagnosis;
import models.Patient;
import models.TreatmentCourse;
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

    public void addTreatmentCourse(TreatmentCourse treatmentCourse) {
        addOneCsv(treatmentCourse.getFileName(), treatmentCourse.toCsvHeader(),
                treatmentCourse.toCsvString());
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        addOneCsv(diagnosis.getFileName(), diagnosis.toCsvHeader(),
                diagnosis.toCsvString());
    }

    public void addAnalysis(BaseAnalysis analysis) {
        addOneCsv(analysis.getFileName(), analysis.toCsvHeader(),
                analysis.toCsvString());
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

    public void updateUser(User updatedUser) {
        updateCsv(updatedUser.getFileName(), info -> {
            String nid = info[0];
            String name = info[1];
            String email = info[2];
            String password = info[3];
            boolean isPatient = Boolean.parseBoolean(info[4]);
            return new User(nid, name, email, password, isPatient);
        }, user -> ((User) user).getNid().equals(updatedUser.getNid()), updatedUser);
    }

    public void updatePatient(Patient updatedPatient) {
        updateCsv(updatedPatient.getFileName(), info -> {
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
        }, patient -> ((Patient) patient).getNid().equals(updatedPatient.getNid()), updatedPatient);
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

    private static void updateCsv(String fileName, Function<String[], BaseModel> createObjectFunc,
            Predicate<BaseModel> filter, BaseModel updatedObject) {
        List<BaseModel> objects = new ArrayList<>();
        BufferedReader bReader = null;
        BufferedWriter bWriter = null;
        boolean headerSkipped = false;
        String headerLine = null;

        try {
            bReader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = bReader.readLine()) != null) {
                if (!headerSkipped) {
                    // Save the header line
                    headerLine = line;
                    objects.add(null); // Placeholder for header line
                    headerSkipped = true;
                } else {
                    String[] info = parseCsvLine(line);
                    BaseModel object = createObjectFunc.apply(info);
                    if (filter.test(object)) {
                        objects.add(updatedObject);
                    } else {
                        objects.add(object);
                    }
                }
            }

            bWriter = new BufferedWriter(new FileWriter(fileName));

            for (BaseModel object : objects) {
                if (object == null) {
                    // Write the header line
                    bWriter.write(headerLine);
                } else {
                    String csvString = object.toCsvString();
                    bWriter.write(csvString);
                }
                bWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
                if (bWriter != null)
                    bWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

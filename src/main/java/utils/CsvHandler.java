package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import models.AppointmentRequest;
import models.BaseAnalysis;
import models.BaseModel;
import models.BioBloodAnalysis;
import models.BloodAnalysis;
import models.Diagnosis;
import models.MedicalHistory;
import models.Patient;
import models.TreatmentCourse;
import models.UrineAnalysis;
import models.User;

public class CsvHandler {
    public CsvHandler() {
    }

    // Wrapper methods
    // Add methods

    public void addUser(User user) {
        addCsv(user.getFileName(), user.toCsvHeader(), user.toCsvString());
    }

    public void addPatient(Patient patient) {
        addCsv(patient.getFileName(), patient.toCsvHeader(), patient.toCsvString());
    }

    public void addAppointmentReq(AppointmentRequest appointmentReq) {
        addCsv(appointmentReq.getFileName(), appointmentReq.toCsvHeader(),
                appointmentReq.toCsvString());
    }

    public void addTreatmentCourse(TreatmentCourse treatmentCourse) {
        addCsv(treatmentCourse.getFileName(), treatmentCourse.toCsvHeader(),
                treatmentCourse.toCsvString());
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        addCsv(diagnosis.getFileName(), diagnosis.toCsvHeader(),
                diagnosis.toCsvString());
    }

    public void addAnalysis(BaseAnalysis analysis) {
        addCsv(analysis.getFileName(), analysis.toCsvHeader(),
                analysis.toCsvString());
    }

    public void addMedicalHistory(MedicalHistory medicalHistory) {
        addCsv(medicalHistory.getFileName(), medicalHistory.toCsvHeader(), medicalHistory.toCsvString());
    }

    // Get one methods

    public User getUser(String userNid) {
        List<User> users = getCsv(new User().getFileName(), info -> {
            String nid = info[0];
            String name = info[1];
            String email = info[2];
            String password = info[3];
            boolean isPatient = Boolean.parseBoolean(info[4]);

            return new User(nid, name, email, password, isPatient);

        }, user -> user.getNid().equals(userNid), false);

        return users.isEmpty() ? null : users.get(0);
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

        }, patient -> patient.getNid().equals(patientNid), false);

        return patients.isEmpty() ? null : patients.get(0);
    }

    public AppointmentRequest getAppointmentReq(String reqUid) {
        List<AppointmentRequest> reqs = getCsv(new AppointmentRequest().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String preferredDoctor = info[2];
            String requestedMedicalDept = info[3];
            String preferredMedicalFacility = info[4];
            String date = info[5];
            String details = info[6];
            boolean isFollowUp = Boolean.parseBoolean(info[7]);

            return new AppointmentRequest(uid, patientNid, preferredDoctor, requestedMedicalDept,
                    preferredMedicalFacility,
                    date, details, isFollowUp);

        }, req -> req.getUid().equals(reqUid), false);

        return reqs.isEmpty() ? null : reqs.get(0);
    }

    public TreatmentCourse getTreatmentCourse(String courseUid) {
        List<TreatmentCourse> courses = getCsv(new TreatmentCourse().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String diagnosis = info[2];
            String treatmentType = info[3];
            String startDate = info[4];
            String endDate = info[5];
            String results = info[6];

            return new TreatmentCourse(uid, patientNid, diagnosis, treatmentType, startDate, endDate, results);
        }, course -> course.getUid().equals(courseUid), false);

        return courses.isEmpty() ? null : courses.get(0);
    }

    public Diagnosis getDiagnosis(String diagnosisUid) {
        List<Diagnosis> diagnoses = getCsv(new Diagnosis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String diagnosis = info[2];
            String date = info[3];
            String labId = info[4];
            String labName = info[5];
            String results = info[6];
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(info[7].split(",")));

            return new Diagnosis(uid, patientNid, diagnosis, date, labId, labName, results, allergies);

        }, diagnosis -> diagnosis.getUid().equals(diagnosisUid), false);

        return diagnoses.isEmpty() ? null : diagnoses.get(0);
    }

    public BloodAnalysis getBloodAnalysis(String analysisUid) {
        List<BloodAnalysis> analyses = getCsv(new BloodAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            double whiteBloodCell = Double.parseDouble(info[7]);
            double redBloodCell = Double.parseDouble(info[8]);
            double haemoglobin = Double.parseDouble(info[9]);
            double lymphocytes = Double.parseDouble(info[10]);

            return new BloodAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    whiteBloodCell,
                    redBloodCell,
                    haemoglobin,
                    lymphocytes);

        }, analysis -> analysis.getUid().equals(analysisUid), false);

        return analyses.isEmpty() ? null : analyses.get(0);
    }

    public BioBloodAnalysis getBioBloodAnalysis(String analysisUid) {
        List<BioBloodAnalysis> analyses = getCsv(new BioBloodAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            double sodium = Double.parseDouble(info[7]);
            double potassium = Double.parseDouble(info[8]);
            double urea = Double.parseDouble(info[9]);
            double creatinine = Double.parseDouble(info[10]);
            double glucose = Double.parseDouble(info[11]);
            double biluribin = Double.parseDouble(info[12]);
            double ast = Double.parseDouble(info[13]);
            double alt = Double.parseDouble(info[14]);

            return new BioBloodAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    sodium, potassium, urea, creatinine, glucose,
                    biluribin, ast, alt);

        }, analysis -> analysis.getUid().equals(analysisUid), false);

        return analyses.isEmpty() ? null : analyses.get(0);
    }

    public UrineAnalysis getUrineAnalysis(String analysisUid) {
        List<UrineAnalysis> analyses = getCsv(new UrineAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            String clarity = info[7];
            String crystals = info[8];
            String bacteria = info[9];
            double ketone = Double.parseDouble(info[10]);
            double protein = Double.parseDouble(info[11]);
            double clinitest = Double.parseDouble(info[12]);
            double whiteBloodCell = Double.parseDouble(info[13]);
            double redBloodCell = Double.parseDouble(info[14]);

            return new UrineAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    clarity, crystals, bacteria, ketone, protein, clinitest,
                    whiteBloodCell,
                    redBloodCell);

        }, analysis -> analysis.getUid().equals(analysisUid), false);

        return analyses.isEmpty() ? null : analyses.get(0);
    }

    public MedicalHistory getMedicalHistory(String historyUid) {
        List<MedicalHistory> medicalHistories = getCsv(new MedicalHistory().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            double patientHeight = Double.parseDouble(info[2]);
            double patientWeight = Double.parseDouble(info[3]);
            String procedureName = info[4];
            String attendingStaff = info[5];
            String medicationName = info[6];
            String wardNo = info[7];
            String majorComplications = info[8];
            String treatmentHistory = info[9];

            return new MedicalHistory(uid,
                    patientNid,
                    patientHeight,
                    patientWeight,
                    procedureName,
                    attendingStaff,
                    medicationName,
                    wardNo,
                    majorComplications,
                    treatmentHistory);

        }, history -> history.getUid().equals(historyUid), false);

        return medicalHistories.isEmpty() ? null : medicalHistories.get(0);
    }

    // Get all methods

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

    public List<AppointmentRequest> getAllAppointmentReqs(String nid) {
        return getCsv(new AppointmentRequest().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String preferredDoctor = info[2];
            String requestedMedicalDept = info[3];
            String preferredMedicalFacility = info[4];
            String date = info[5];
            String details = info[6];
            boolean isFollowUp = Boolean.parseBoolean(info[7]);

            return new AppointmentRequest(uid, patientNid, preferredDoctor, requestedMedicalDept,
                    preferredMedicalFacility,
                    date, details, isFollowUp);

        }, req -> req.getPatientNid().equals(nid), true);
    }

    public List<TreatmentCourse> getAllTreatmentCourses(String nid) {
        return getCsv(new TreatmentCourse().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String diagnosis = info[2];
            String treatmentType = info[3];
            String startDate = info[4];
            String endDate = info[5];
            String results = info[6];

            return new TreatmentCourse(uid, patientNid, diagnosis, treatmentType, startDate, endDate, results);
        }, course -> course.getPatientNid().equals(nid), true);
    }

    public List<Diagnosis> getAllDiagnoses(String nid) {
        return getCsv(new Diagnosis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String diagnosis = info[2];
            String date = info[3];
            String labId = info[4];
            String labName = info[5];
            String results = info[6];
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(info[7].split(",")));

            return new Diagnosis(uid, patientNid, diagnosis, date, labId, labName, results, allergies);

        }, diagnosis -> diagnosis.getPatientNid().equals(nid), true);
    }

    public List<BloodAnalysis> getAllBloodAnalyses(String nid) {
        return getCsv(new BloodAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            double whiteBloodCell = Double.parseDouble(info[7]);
            double redBloodCell = Double.parseDouble(info[8]);
            double haemoglobin = Double.parseDouble(info[9]);
            double lymphocytes = Double.parseDouble(info[10]);

            return new BloodAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    whiteBloodCell,
                    redBloodCell,
                    haemoglobin,
                    lymphocytes);

        }, analysis -> analysis.getPatientNid().equals(nid), true);
    }

    public List<BioBloodAnalysis> getAllBioBloodAnalyses(String nid) {
        return getCsv(new BioBloodAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            double sodium = Double.parseDouble(info[7]);
            double potassium = Double.parseDouble(info[8]);
            double urea = Double.parseDouble(info[9]);
            double creatinine = Double.parseDouble(info[10]);
            double glucose = Double.parseDouble(info[11]);
            double biluribin = Double.parseDouble(info[12]);
            double ast = Double.parseDouble(info[13]);
            double alt = Double.parseDouble(info[14]);

            return new BioBloodAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    sodium, potassium, urea, creatinine, glucose,
                    biluribin, ast, alt);

        }, analysis -> analysis.getPatientNid().equals(nid), true);
    }

    public List<UrineAnalysis> getAllUrineAnalyses(String nid) {
        return getCsv(new UrineAnalysis().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            String labId = info[2];
            String labName = info[3];
            String labAddress = info[4];
            String date = info[5];
            String clarity = info[7];
            String crystals = info[8];
            String bacteria = info[9];
            double ketone = Double.parseDouble(info[10]);
            double protein = Double.parseDouble(info[11]);
            double clinitest = Double.parseDouble(info[12]);
            double whiteBloodCell = Double.parseDouble(info[13]);
            double redBloodCell = Double.parseDouble(info[14]);

            return new UrineAnalysis(uid, patientNid, labId, labName,
                    labAddress,
                    date,
                    clarity, crystals, bacteria, ketone, protein, clinitest,
                    whiteBloodCell,
                    redBloodCell);

        }, analysis -> analysis.getPatientNid().equals(nid), true);
    }

    public List<BaseAnalysis> getAllAnalyses(String nid) {
        List<BaseAnalysis> analyses = new ArrayList<BaseAnalysis>();
        analyses.addAll(getAllBioBloodAnalyses(nid));
        analyses.addAll(getAllBloodAnalyses(nid));
        analyses.addAll(getAllUrineAnalyses(nid));

        return analyses;
    }

    public List<MedicalHistory> getAllMedicalHistory(String nid) {
        return getCsv(new MedicalHistory().getFileName(), info -> {
            String uid = info[0];
            String patientNid = info[1];
            double patientHeight = Double.parseDouble(info[2]);
            double patientWeight = Double.parseDouble(info[3]);
            String procedureName = info[4];
            String attendingStaff = info[5];
            String medicationName = info[6];
            String wardNo = info[7];
            String majorComplications = info[8];
            String treatmentHistory = info[9];

            return new MedicalHistory(uid,
                    patientNid,
                    patientHeight,
                    patientWeight,
                    procedureName,
                    attendingStaff,
                    medicationName,
                    wardNo,
                    majorComplications,
                    treatmentHistory);

        }, history -> history.getPatientNid().equals(nid), true);
    }

    // Update methods

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

    // Delete methods

    public void deleteBioBloodAnalysis(String uid) {
        String filename = new BioBloodAnalysis().getFileName();
        deleteCsv(filename, uid);
    }

    public void deleteBloodAnalysis(String uid) {
        String filename = new BloodAnalysis().getFileName();
        deleteCsv(filename, uid);
    }

    public void deleteUrineAnalysis(String uid) {
        String filename = new UrineAnalysis().getFileName();
        deleteCsv(filename, uid);
    }

    public void deleteDiagnosis(String uid) {
        String filename = new Diagnosis().getFileName();
        deleteCsv(filename, uid);
    }

    public void deleteMedicalHistory(String uid) {
        String filename = new MedicalHistory().getFileName();
        deleteCsv(filename, uid);
    }

    public void deleteTreatmentCourse(String uid) {
        String filename = new TreatmentCourse().getFileName();
        deleteCsv(filename, uid);
    }

    // Implementation methods

    public static String[] parseCsvLine(String line) {
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

    private static void addCsv(String fileName, String header, String data) {
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
            File file = new File(fileName);
            if (!file.exists())
                return objects;

            bReader = new BufferedReader(new FileReader(file));
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
                    headerLine = line;
                    objects.add(null);
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

    private static void deleteCsv(String fileName, String id) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());

                Iterator<String> iterator = lines.iterator();
                while (iterator.hasNext()) {
                    String line = iterator.next();
                    if (line.contains(id)) {
                        iterator.remove();
                        break;
                    }
                }
                Files.write(file.toPath(), lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(fileName + " does not exist.");
        }
    }
}

package DAO;

import model.Patient;

import java.util.List;

public interface PatientDAO {
    List<Patient> getAllPatients();
    void addPatient(Patient patient);
    void deletePatient(int id);
    void editPatient(Patient patient);
}
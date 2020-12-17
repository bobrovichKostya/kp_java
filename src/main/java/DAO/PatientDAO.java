package DAO;

import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDAO {
    List<Patient> getAllPatients() throws SQLException;
    void addPatient(Patient patient) throws SQLException;
    void deletePatient(int id) throws SQLException;
    void editPatient(Patient patient) throws SQLException;
    Patient getById(int id) throws SQLException;
}

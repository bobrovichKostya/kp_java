package DAO;

import model.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDAO {
    List<Doctor> getAllDoctors() throws SQLException;
}

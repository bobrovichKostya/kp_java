package DAO;

import model.Medicine;
import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO {
    List<Medicine> getAllMedicine() throws SQLException;
    void addMedicine(Medicine medicine) throws SQLException;
    void deleteMedicine(int id) throws SQLException;
    void editMedicine(Medicine medicine) throws SQLException;
    Medicine getById(int id) throws SQLException;
}

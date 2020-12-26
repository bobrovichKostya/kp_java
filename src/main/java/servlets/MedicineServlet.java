package servlets;

import DAO.MedicineDAO;
import DAO.MedicineDAOImpl;
import model.Medicine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/medList", "/deleteMed", "/newMed", "/editMed", "/updateMed", "/insertMed"})
public class MedicineServlet extends HttpServlet {
    private String url = "jdbc:mysql://localhost:3306/test1?serverTimezone=Europe/Minsk&useSSL=false";
    private String username = "root";
    private String password = "root";
    MedicineDAO medicineDAO;

    @Override
    public void init(){
        medicineDAO = new MedicineDAOImpl(url, username, password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try{
            switch (action){
                case "/medList":
                    allMed(req, resp);
                    break;
                case "/newMed":
                    newMed(req, resp);
                    break;
                case "/editMed":
                    editMed(req, resp);
                    break;
                case "/updateMed":
                    updateMed(req, resp);
                    break;
                case "/insertMed":
                    insertMedicine(req, resp);
                    break;
                case "/deleteMed":
                    deleteMedicine(req, resp);
                    break;
            }
        } catch (SQLException e){
            System.err.println(e);
        }

    }

    private void insertMedicine(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        int count = Integer.parseInt(req.getParameter("count"));
        boolean morning = Boolean.parseBoolean(req.getParameter("morning"));
        boolean noon = Boolean.parseBoolean(req.getParameter("noon"));
        boolean evening = Boolean.parseBoolean(req.getParameter("evening"));

        Medicine medicine = new Medicine(name, count, morning, noon, evening);
        medicineDAO.addMedicine(medicine);
        resp.sendRedirect("/medList");
    }

    private void updateMed(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int count = Integer.parseInt(req.getParameter("count"));
        boolean morning = Boolean.parseBoolean(req.getParameter("type"));
        boolean noon = Boolean.parseBoolean(req.getParameter("type"));
        boolean evening = Boolean.parseBoolean(req.getParameter("type"));

        Medicine medicine = new Medicine(id, name, count, morning, noon, evening);
        medicineDAO.editMedicine(medicine);
        resp.sendRedirect("/medList");
    }

    private void editMed(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Medicine currentMed = medicineDAO.getById(id);
        req.setAttribute("medicine", currentMed);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/editMed.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void newMed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/editMed.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void deleteMedicine(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        medicineDAO.deleteMedicine(id);
        resp.sendRedirect("/medList");
    }

    private void allMed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Medicine> medList = medicineDAO.getAllMedicine();
        req.setAttribute("medicine", medList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/medList.jsp");
        requestDispatcher.forward(req, resp);
    }

}

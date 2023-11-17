package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import DAO.VehicleDAO;
import VO.VehicleVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VehicleController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String operation = request.getParameter("operation");
            VehicleDAO dao = new VehicleDAO();
            switch (operation) {
                case "find-all":
                    request.setAttribute("list", dao.findAll());
                    RequestDispatcher rd = request.getRequestDispatcher("/list_vehicles.jsp");
                    rd.forward(request, response);
                    break;
                case "create":
                    String make = request.getParameter("make");
                    String model = request.getParameter("model");
                    String plate = request.getParameter("plate");     
                    String daily_rate = request.getParameter ("daily_rate"); 
                    VehicleVO newVehicle = new VehicleVO();                    
                    newVehicle.setMake(make);
                    newVehicle.setModel(model);
                    newVehicle.setPlate(plate);
                    newVehicle.setDailyRate(daily_rate);
                    dao.create(newVehicle);
                    response.sendRedirect("VehicleController?operation=find-all");             
                    break;
                case "find-by-id":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("vehicle", dao.findById(id));
                    RequestDispatcher r = request.getRequestDispatcher("/update_vehicle.jsp");
                    r.forward(request, response);  
                    break;
                case "update":
                    id = Integer.parseInt(request.getParameter("id"));
                    make = request.getParameter("make");
                    model = request.getParameter("model");
                    plate = request.getParameter("plate");
                    daily_rate = request.getParameter("daily_rate");
                    VehicleVO updatedVehicle = new VehicleVO();
                    updatedVehicle.setId(id);
                    updatedVehicle.setMake(make);
                    updatedVehicle.setModel(model);
                    updatedVehicle.setPlate(plate);
                    updatedVehicle.setDailyRate(daily_rate);
                    dao.update(updatedVehicle);
                    response.sendRedirect("VehicleController?operation=find-all");
                    break;
                case "delete": 
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VehicleController?operation=find-all");
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

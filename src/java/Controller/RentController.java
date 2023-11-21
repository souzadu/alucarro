package Controller;

import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import DAO.RentDAO;
import DAO.VehicleDAO;
import VO.CustomerVO;
import VO.RentCreateVO;
import VO.RentVO;
import VO.VehicleVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String operation = request.getParameter("operation");
            CustomerDAO cDAO = new CustomerDAO();
            VehicleDAO vDAO = new VehicleDAO();
            RentDAO dao = new RentDAO();            
            switch (operation) {
                case "find-all":
                    request.setAttribute("list", dao.findAll());
                    RequestDispatcher rd = request.getRequestDispatcher("/list_rents.jsp");
                    rd.forward(request, response);
                    break;
                case "create-step-one":
                    request.setAttribute("list", vDAO.findAll());
                    RequestDispatcher rdC = request.getRequestDispatcher("/create_rent_step_one.jsp");
                    rdC.forward(request, response);
                    break;
                case "create-step-two":
                    String vehicleId = request.getParameter("vehicle");                    
                    String initialDateStr = request.getParameter("initial_date");
                    String finalDateStr = request.getParameter("final_date");
                    String cpf = request.getParameter("cpf");
                    VehicleVO vehicle = vDAO.findById(Integer.parseInt(vehicleId));
                    CustomerVO customer = cDAO.findByCPF(cpf);
                    
                    LocalDate initialDate = LocalDate.parse(initialDateStr);
                    LocalDate finalDate = LocalDate.parse(finalDateStr);
                    long numOfDays = ChronoUnit.DAYS.between(initialDate, finalDate) + 1;
                    float totalRate = vehicle.getDailyRate() * numOfDays;
                    
                    request.setAttribute("vehicle", vehicle);
                    request.setAttribute("initial_date", initialDateStr);
                    request.setAttribute("final_date", finalDateStr);
                    request.setAttribute("total_rate", totalRate);
                    request.setAttribute("cpf", cpf);
                    request.setAttribute("customer", customer);
                    RequestDispatcher rdTwo = request.getRequestDispatcher("/create_rent_step_two.jsp");
                    rdTwo.forward(request, response);
                    break;
                case "create":                    
                    String initialDStr = request.getParameter("initial_date");
                    String finalDStr = request.getParameter("final_date");
                    LocalDate initDt = LocalDate.parse(initialDStr);
                    LocalDate finDt = LocalDate.parse(finalDStr);
                    
                    RentCreateVO newRent = new RentCreateVO();
                    newRent.setInitialDate(initDt);
                    newRent.setFinalDate(finDt);
                    newRent.setTotalRate(Float.parseFloat(request.getParameter("total_rate").replaceAll("[^\\d.]", "")));
                    newRent.setIdCustomer(Integer.parseInt(request.getParameter("customer_id")));
                    newRent.setIdVehicle(Integer.parseInt(request.getParameter("vehicle")));
                    newRent.setCardOwner(request.getParameter("card_owner"));
                    newRent.setCardNumber(request.getParameter("card_number"));
                    newRent.setCardExp(request.getParameter("card_exp"));
                    newRent.setCardCvv(Integer.parseInt(request.getParameter("card_cvv")));

                    dao.create(newRent);
                    response.sendRedirect("RentController?operation=find-all");
                    break;
                case "update-step-one":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("rent", dao.findById(id));
                    request.setAttribute("vehicles", vDAO.findAll());
                    RequestDispatcher r = request.getRequestDispatcher("/update_rent_step_one.jsp");
                    r.forward(request, response);  
                    break;
                /*case "update":
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
                    break;*/
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("RentController?operation=find-all");
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

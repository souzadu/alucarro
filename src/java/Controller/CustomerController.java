package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import DAO.CustomerDAO;
import VO.CustomerVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String operation = request.getParameter("operation");
            CustomerDAO dao = new CustomerDAO();
            switch (operation) {
                case "find-all":
                    request.setAttribute("list", dao.findAll());
                    RequestDispatcher rd = request.getRequestDispatcher("/list_customers.jsp");
                    rd.forward(request, response);
                    break;
                case "create":
                    String name = request.getParameter("name");
                    String cpf = request.getParameter("cpf");
                    String phone = request.getParameter("phone");                    
                    CustomerVO newCustomer = new CustomerVO();                    
                    newCustomer.setName(name);
                    newCustomer.setCpf(cpf);
                    newCustomer.setPhone(phone);
                    dao.create(newCustomer);
                    response.sendRedirect("CustomerController?operation=find-all");             
                    break;
                case "find-by-id":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("customer", dao.findById(id));
                    RequestDispatcher r = request.getRequestDispatcher("/update_customer.jsp");
                    r.forward(request, response);  
                    break;
                case "update":
                    id = Integer.parseInt(request.getParameter("id"));
                    name = request.getParameter("name");
                    cpf = request.getParameter("cpf");
                    phone = request.getParameter("phone");
                    CustomerVO updatedCustomer = new CustomerVO();
                    updatedCustomer.setId(id);
                    updatedCustomer.setName(name);
                    updatedCustomer.setCpf(cpf);
                    updatedCustomer.setPhone(phone);
                    dao.update(updatedCustomer);
                    response.sendRedirect("CustomerController?operation=find-all");
                    break;
                case "delete": 
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("CustomerController?operation=find-all");
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

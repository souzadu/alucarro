package DAO;

import Database.Database;
import VO.CustomerVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {   
    public ArrayList<CustomerVO> findAll() {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Database().connect();
            if (con != null) {
                String sql = "SELECT id, name, cpf, phone FROM customers";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<CustomerVO> customers = new ArrayList<>();
                while (rs.next()) {
                    CustomerVO customer = new CustomerVO();      
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setCpf(rs.getString("cpf"));
                    customer.setPhone(rs.getString("phone"));
                    customers.add(customer);
                }
                con.close();
                return customers;
            }else{
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados de clientes: " + erro.getMessage());
            return null;
        }
    }
    
    public boolean create(CustomerVO customer) {
        Connection con = new Database().connect();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "INSERT INTO customers (name, cpf, phone) VALUES (?, ?, ?)";                
                ps = con.prepareStatement(sql);
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getCpf());
                ps.setString(3, customer.getPhone());                
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
}
package DAO;

import Database.Database;
import VO.RentCreateVO;
import VO.RentUpdateVO;
import VO.RentVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentDAO {   
    public ArrayList<RentVO> findAll() {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Database().connect();
            if (con != null) {
                String sql = "SELECT id, id_customer, id_vehicle, initial_date, final_date, status, total_rate FROM rents";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<RentVO> rents = new ArrayList<>();
                while (rs.next()) {
                    String customer_sql = "SELECT name, cpf FROM customers WHERE id=?";
                    PreparedStatement cPs;
                    ResultSet cRs;
                    cPs = con.prepareStatement(customer_sql);
                    cPs.setInt(1, rs.getInt("id_customer"));
                    cRs = cPs.executeQuery();
                    cRs.next();
                    
                    String vehicle_sql = "SELECT make, model FROM vehicles WHERE id=?";
                    PreparedStatement vPs;
                    ResultSet vRs;
                    vPs = con.prepareStatement(vehicle_sql);
                    vPs.setInt(1, rs.getInt("id_vehicle"));
                    vRs = vPs.executeQuery();
                    vRs.next();
                    
                    RentVO rent = new RentVO();
                    rent.setId(rs.getInt("id"));
                    rent.setCar(vRs.getString("make") + " " + vRs.getString("model"));
                    rent.setCustomerName(cRs.getString("name") + " - " + cRs.getString("cpf"));
                    rent.setInitialDate(rs.getDate("initial_date"));
                    rent.setFinalDate(rs.getDate("final_date"));
                    rent.setStatus(rs.getBoolean("status"));
                    rent.setTotalRate(rs.getFloat("total_rate"));
                    rents.add(rent);
                }
                con.close();
                return rents;
            }else{
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados de aluguéis: " + erro.getMessage());
            return null;
        }
    }
    
    public boolean create(RentCreateVO rent) {        
        Connection con = new Database().connect();        
        if (con != null) {
            try {                
                PreparedStatement ps;
                String sql = "INSERT INTO rents (initial_date, final_date, status, total_rate, id_customer, id_vehicle, card_owner, card_number, card_exp, card_cvv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";                
                ps = con.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(rent.getInitialDate()));
                ps.setDate(2, java.sql.Date.valueOf(rent.getFinalDate()));
                ps.setBoolean(3, false);
                ps.setFloat(4, rent.getTotalRate());
                ps.setInt(5, rent.getIdCustomer());
                ps.setInt(6, rent.getIdVehicle());
                ps.setString(7, rent.getCardOwner());
                ps.setString(8, rent.getCardNumber());
                ps.setString(9, rent.getCardExp());
                ps.setInt(10, rent.getCardCvv());
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
    
    
    public boolean delete(int id){
        Connection con = new Database().connect();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "DELETE FROM rents WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);             
                if(ps.executeUpdate()!=0){
                    con.close();
                    return true;
                }else{
                    return false;
                }               
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public RentVO findById(int id) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Database().connect();
            if (con != null) {
                String sql = "SELECT id, initial_date, final_date, status, total_rate, id_customer, id_vehicle, card_owner, card_number, card_exp, card_cvv FROM rents WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                
                String customer_sql = "SELECT name, cpf FROM customers WHERE id=?";
                PreparedStatement cPs;
                ResultSet cRs;
                cPs = con.prepareStatement(customer_sql);
                cPs.setInt(1, rs.getInt("id_customer"));
                cRs = cPs.executeQuery();
                cRs.next();

                String vehicle_sql = "SELECT make, model, plate, daily_rate FROM vehicles WHERE id=?";
                PreparedStatement vPs;
                ResultSet vRs;
                vPs = con.prepareStatement(vehicle_sql);
                vPs.setInt(1, rs.getInt("id_vehicle"));
                vRs = vPs.executeQuery();
                vRs.next();
                
                RentVO rent = new RentVO();
                rent.setId(rs.getInt("id"));
                rent.setCar(vRs.getString("make") + " " + vRs.getString("model") + " - " + vRs.getString("plate") + " - R$" + vRs.getFloat("daily_rate"));
                rent.setIdVehicle(rs.getInt("id_vehicle"));
                rent.setIdCustomer(rs.getInt("id_customer"));
                rent.setCustomerName(cRs.getString("name"));
                rent.setCustomerCpf(cRs.getString("cpf"));
                rent.setInitialDate(rs.getDate("initial_date"));
                rent.setFinalDate(rs.getDate("final_date"));
                rent.setStatus(rs.getBoolean("status"));
                rent.setTotalRate(rs.getFloat("total_rate"));
                rent.setCardOwner(rs.getString("card_owner"));
                rent.setCardNumber(rs.getString("card_number"));
                rent.setCardExp(rs.getString("card_exp"));
                rent.setCardCvv(rs.getString("card_cvv"));

                con.close();
                return rent;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }    
    public boolean update(RentUpdateVO rent) {
        Connection con = new Database().connect();
        if (con != null) {
            try {                
                PreparedStatement ps;
                String sql = "UPDATE rents SET initial_date=?, final_date=?, status=?, total_rate=?, id_customer=?, id_vehicle=?, card_owner=?, card_number=?, card_exp=?, card_cvv=? WHERE id=?";
                ps = con.prepareStatement(sql);
                ps.setDate(1, java.sql.Date.valueOf(rent.getInitialDate()));
                ps.setDate(2, java.sql.Date.valueOf(rent.getFinalDate()));
                ps.setBoolean(3, rent.isStatus());
                ps.setFloat(4, rent.getTotalRate());
                ps.setInt(5, rent.getIdCustomer());
                ps.setInt(6, rent.getIdVehicle());
                ps.setString(7, rent.getCardOwner());
                ps.setString(8, rent.getCardNumber());
                ps.setString(9, rent.getCardExp());              
                ps.setInt(10, rent.getCardCvv());
                ps.setInt(11, rent.getId());
                if (ps.executeUpdate() != 0) {
                    System.out.println("execute");
                    con.close();
                    return true;
                } else {
                    System.out.println("false");
                    return false;
                }
            } catch (SQLException erro) {
                System.out.println(erro);
                return false;
            }
        } else {
            return false;
        }
    }
}
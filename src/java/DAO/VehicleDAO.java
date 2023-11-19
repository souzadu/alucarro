package DAO;

import Database.Database;
import VO.VehicleVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAO {   
    public ArrayList<VehicleVO> findAll() {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Database().connect();
            if (con != null) {
                String sql = "SELECT id, make, model, plate, daily_rate FROM vehicles";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<VehicleVO> vehicles = new ArrayList<>();
                while (rs.next()) {
                    VehicleVO vehicle = new VehicleVO();      
                    vehicle.setId(rs.getInt("id"));
                    vehicle.setMake(rs.getString("make"));
                    vehicle.setModel(rs.getString("model"));
                    vehicle.setPlate(rs.getString("plate"));
                    vehicle.setDailyRate(rs.getString("daily_rate"));
                    vehicles.add(vehicle);
                }
                con.close();
                return vehicles;
            }else{
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados de veículos: " + erro.getMessage());
            return null;
        }
    }
    
    public boolean create(VehicleVO vehicle) {
        Connection con = new Database().connect();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "INSERT INTO vehicles (make, model, plate, daily_rate) VALUES (?, ?, ?, ?)";                
                ps = con.prepareStatement(sql);
                ps.setString(1, vehicle.getMake());
                ps.setString(2, vehicle.getModel());
                ps.setString(3, vehicle.getPlate());              
                ps.setFloat(4, vehicle.getDailyRate()); 
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
                String sql = "DELETE FROM vehicles WHERE id = ?";
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
    
    public VehicleVO findById(int id) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Database().connect();
            if (con != null) {
                String sql = "SELECT id,make, model, plate, daily_rate FROM vehicles WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();                
                VehicleVO vehicle = new VehicleVO();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setPlate(rs.getString("plate"));
                vehicle.setDailyRate(rs.getString("daily_rate"));
                con.close();
                return vehicle;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }
    
    public boolean update(VehicleVO vehicle) {
        Connection con = new Database().connect();
        if (con != null) {
            try {                
                PreparedStatement ps;
                String sql = "UPDATE vehicles SET make=?, model=?, plate=?, daily_rate=? WHERE id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, vehicle.getMake());
                ps.setString(2, vehicle.getModel());
                ps.setString(3, vehicle.getPlate());    
                ps.setFloat(4, vehicle.getDailyRate()); 
                ps.setInt(5, vehicle.getId());                
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
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection dbConnection = null;
    private final String db = "dbalucarro";
    private final String dbuser = "root";
    private final String dbpassword = "root";

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + 
                    this.db + "?useSSL=false",
                this.dbuser,  this.dbpassword);
        } catch (ClassNotFoundException e1) {
            System.out.println("Erro com o driver: " + e1.getMessage());
        } catch (SQLException e2) {
            System.out.println("Erro na tentativa de conexão: " + e2.getMessage());
        }
        
        return dbConnection;
    }
}
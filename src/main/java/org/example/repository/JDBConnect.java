package org.example.repository;

import java.net.URL;
import java.sql.*;

public class JDBConnect {
    public Connection conn;
    public Statement stmt;
    public PreparedStatement pstm;
    public ResultSet rs;

    public JDBConnect() {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            System.out.println(resource);
            String split = resource.toString().split("/out")[0];
            split = split.split("file:")[1];
            System.out.println(split);
            conn = DriverManager.getConnection("jdbc:oracle:thin:@xx69ymgik72314ol_medium?TNS_ADMIN=/Users/baesua/dev/Wallet_XX69YMGIK72314OL","ADMIN","Ahmooguna1234!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

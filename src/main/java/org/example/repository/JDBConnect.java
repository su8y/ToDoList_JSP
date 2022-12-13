package org.example.repository;

import java.io.File;
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
            String os = System.getProperty("os.name").toLowerCase();
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            String path;
            if(os.contains("mac")){
                path = resource.toString().split(":")[1];
            }else{
                path = resource.toString().split("C:")[1];
            }
            String split[] = path.split("/");
            String realPath = "";
            for(int i = 0 ; i < 3; i++){
                realPath += split[i];
                realPath += File.separator;
            }
            realPath += "dev";
            realPath += File.separator;
            realPath += "Wallet_XX69YMGIK72314OL";
            System.out.println(realPath);
            conn = DriverManager.getConnection("jdbc:oracle:thin:@xx69ymgik72314ol_medium?TNS_ADMIN="+realPath,"ADMIN","Ahmooguna1234!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

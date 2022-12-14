package org.example.config;

import org.example.repository.JdbcMemberRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContextLoaderListener implements ServletContextListener {
    Connection conn;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("@@@ contextInitialized !!");
        ServletContext sc = sce.getServletContext();
        String id = sc.getInitParameter("username");
        String pw = sc.getInitParameter("password");
        String realPath = getWalletRealPath();

        makeConnection(sc, id, pw, realPath);

        sc.setAttribute("conn",conn);
    }

    private void makeConnection(ServletContext sc, String id, String pw, String realPath) {
        try {
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(realPath, id, pw);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getWalletRealPath() {
        String os = System.getProperty("os.name").toLowerCase();
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        String path;
        if (os.contains("mac")) {
            path = resource.toString().split(":")[1];
        } else {
            path = resource.toString().split("C:")[1];
        }
        String split[] = path.split("/");
        String realPath = "";
        for (int i = 0; i < 3; i++) realPath += split[i] + "/";
        realPath += "dev" + "/" +"Wallet_XX69YMGIK72314OL";
        realPath = "jdbc:oracle:thin:@xx69ymgik72314ol_medium?TNS_ADMIN=" + realPath;
        return realPath;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

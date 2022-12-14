package org.example.config;

import org.example.repository.JdbcMemberRepository;
import org.example.repository.JdbcToDoRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppInitServlet extends HttpServlet {
    JdbcMemberRepository memberRepository;
    JdbcToDoRepository toDoRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init");
        ServletContext sc = config.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");

        memberRepository = new JdbcMemberRepository();
        memberRepository.setConn(conn);
        sc.setAttribute("memberRepository", memberRepository);

        toDoRepository = new JdbcToDoRepository();
        toDoRepository.setConn(conn);
        sc.setAttribute("toDoRepository",toDoRepository);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

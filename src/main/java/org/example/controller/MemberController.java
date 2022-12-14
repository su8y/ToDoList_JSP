package org.example.controller;

import org.example.model.Member;
import org.example.repository.JdbcMemberRepository;
import org.example.repository.MemberRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.IllegalFormatFlagsException;

@WebServlet(name = "ToDoController", value = "/member")
public class MemberController extends HttpServlet {
    ServletContext sc ;
    JdbcMemberRepository memberRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        sc = this.getServletContext();
        memberRepository = (JdbcMemberRepository) sc.getAttribute("memberRepository");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
            Object obj = parser.parse(sb.toString());
            json = (JSONObject) obj;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String id = (String) json.get("id");
        String pw = (String) json.get("pw");


        Member byId = null;
        JSONObject object = new JSONObject();
        try {
            byId = memberRepository.findById(id);
            if (byId != null) {
                if (byId.getMPw().equals(pw)) {
                    object.put("status_code", "SUCCESS");
                    response.getWriter().write(object.toJSONString());
                } else {
                    object.put("status_code", "FAIL");
                    object.put("error", "로그인 실패");
                    response.getWriter().write(object.toJSONString());
                }
            }
        } catch (Exception e) {
            System.out.println("hi");
            object.put("status_code", "FAIL");
            object.put("error", "아이디가 없습니다.");
            response.setStatus(202);
            response.getWriter().write(object.toJSONString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            sb.append(line);
        }
        try {
            Object obj = parser.parse(sb.toString());
            json = (JSONObject) obj;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String id = (String) json.get("id");
        String pw = (String) json.get("pw");
        String name = (String) json.get("name");
        String email = (String) json.get("email");
        System.out.println(id);

        Member member = new Member(id, pw, name, email);
        try {
            memberRepository.save(member);
            JSONObject object = new JSONObject();
            object.put("status_code", "SUCCESS");
            response.setStatus(202);
            response.getWriter().write(JSONObject.toJSONString(object));
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("status_code", "FAIL");
            object.put("error", "이미 등록된 회원입니다.");
            object.put("error_message",e);
            response.setStatus(202);
            response.getWriter().write(JSONObject.toJSONString(object));
        }
    }
}

package org.example.controller;

import org.example.model.Member;
import org.example.repository.JdbcMemberRepository;
import org.example.repository.MemberRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ToDoController", value = "/member")
public class MemberController extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        JSONParser parser = new JSONParser();
        MemberRepository memberRepository = new JdbcMemberRepository();
        JSONObject json = null;
        while((line = reader.readLine()) != null){
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



        Member byId = memberRepository.findById(id);
        if(byId.getMPw().equals(pw)){
            JSONObject object = new JSONObject();
            object.put("message", "성공");
            response.getWriter().write(object.toJSONString());
        }
        else {
            JSONObject object = new JSONObject();
            object.put("message", "실패");
            response.getWriter().write(object.toJSONString());

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        while((line = reader.readLine()) != null){
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


        MemberRepository memberRepository = new JdbcMemberRepository();
        Member member = new Member(id,pw,name,email);
        memberRepository.save(member);
    }
}
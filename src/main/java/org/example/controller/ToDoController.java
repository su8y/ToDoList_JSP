package org.example.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.model.Member;
import org.example.repository.JdbcMemberRepository;
import org.example.repository.MemberRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ToDoController", value = "/member")
public class ToDoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberRepository memberRepository = new JdbcMemberRepository();
        String id = request.getParameter("id");
        String pw = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Member member = new Member();
        member.builder().mName(name).mId(id).mPw(pw).mEmail(email);
        memberRepository.save(member);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));

        String id = request.getParameter("id");
        String pw = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        System.out.println(id);
//        MemberRepository memberRepository = new JdbcMemberRepository();
        Member member = new Member();
        member.builder().mName("hello").mId(id).mPw(pw).mEmail(email);
//        memberRepository.save(member);
        response.getWriter().write(member.toString());
        String jsonTxt = "{\"code\":\"200\", \"msg\":\"success\"}";
//        request.setAttribute("data", jsonTxt);
//        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}

package org.example.controller;

import org.example.model.Member;
import org.example.repository.JdbcMemberRepository;
import org.example.repository.MemberRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ToDoController", value = "/member")
public class ToDoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberRepository memberRepository = new JdbcMemberRepository();
        Member member = new Member("qotndk","qotndk","qotndk","qotndk");
        memberRepository.save(member);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-*");

    }
}

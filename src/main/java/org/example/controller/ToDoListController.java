package org.example.controller;

import org.example.repository.JdbcToDoRepository;
import org.example.repository.ToDoRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/toDo")
public class ToDoListController extends HttpServlet {
    ToDoRepository toDoRepository;
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        toDoRepository = new JdbcToDoRepository();


    }
}

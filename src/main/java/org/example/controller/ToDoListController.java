package org.example.controller;

import oracle.jdbc.internal.XSCacheOutput;
import org.example.model.ToDo;
import org.example.repository.JdbcToDoRepository;
import org.example.repository.ToDoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/toDo")
public class ToDoListController extends HttpServlet {
    JdbcToDoRepository toDoRepository;
    ServletContext sc;
    @Override
    public void init() throws ServletException {
        super.init();
        sc = getServletContext();
        toDoRepository = (JdbcToDoRepository) sc.getAttribute("toDoRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List<ToDo> list = toDoRepository.findToDosByUsername(username);

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        if(list.isEmpty()){
            object.put("todo",null);

        }else{
            for(ToDo t : list){
                JSONObject data = new JSONObject();
                data.put("t_id" , t.getToDoId());
                data.put("m_name" , t.getM_name());
                data.put("content" , t.getToDo());
                data.put("status" , t.getStatus());
                array.add(data);
            }
            object.put("todo", array);
        }
        response.getWriter().write(JSONObject.toJSONString(object));



    }
}

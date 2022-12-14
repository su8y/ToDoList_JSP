package org.example.controller;

import org.example.model.ToDo;
import org.example.repository.JdbcToDoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                data.put("m_name" , t.getM_id());
                data.put("content" , t.getToDo());
                data.put("status" , t.getStatus());
                array.add(data);
            }
            object.put("todo", array);
        }
        response.getWriter().write(JSONObject.toJSONString(object));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        JSONParser parser = new JSONParser();
        JSONObject req_json = new JSONObject();
        String line;
        while((line = reader.readLine()) != null){
            sb.append(line);
        }
        try {
            Object parse = parser.parse(sb.toString());
            req_json = (JSONObject) parse;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        Map<Object, Object> map = new HashMap<>();
//        for (Object o : req_json.keySet()) {
//            map.put(o,req_json.get(o));
//        }
        ToDo req_toDo = ToDo.builder()
                .toDo(req_json.getOrDefault("content", "null").toString())
                .m_id(req_json.getOrDefault("user_id", "undefined").toString())
                .build();
        toDoRepository.save(req_toDo);

        JSONObject res_json = new JSONObject();
        res_json.put("message" ,"success");
        response.setStatus(202);
        response.getWriter().write(res_json.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
    }
}

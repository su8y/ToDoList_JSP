package org.example.repository;

import org.example.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
    int save(ToDo toDo);
    List<ToDo> findAll();
    List<ToDo> findToDosByUsername(String username);
    List<ToDo> findToDoById(long toDoId);
    int delete(ToDo toDo);
    int updateToDo(ToDo toDo);
}

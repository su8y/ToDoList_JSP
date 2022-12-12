package org.example.repository;

import org.example.model.ToDo;

import java.util.Optional;

public interface ToDoRepository {
    ToDo save(ToDo toDo);
    Optional<ToDo> findAll();
    Optional<ToDo> findToDosByUsername(String username);
    Optional<ToDo> findToDoById(long toDoId);
    int delete(ToDo toDo);
    int updateToDo(ToDo toDo);
}

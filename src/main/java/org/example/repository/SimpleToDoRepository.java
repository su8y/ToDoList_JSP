package org.example.repository;

import org.example.model.ToDo;

import java.util.*;

public class SimpleToDoRepository implements ToDoRepository{
    List<ToDo> list = new ArrayList<>();
    long sequence = 0;
    @Override
    public ToDo save(ToDo toDo) {
        toDo.builder().toDoId(sequence++);
        list.add(toDo);
        return toDo;
    }

    @Override
    public Optional<ToDo> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<ToDo> findToDosByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<ToDo> findToDoById(long toDoId) {
        return Optional.empty();
    }


    @Override
    public int delete(ToDo toDo) {
        return 0;
    }

    @Override
    public int updateToDo(ToDo toDo) {
        return 0;
    }
}

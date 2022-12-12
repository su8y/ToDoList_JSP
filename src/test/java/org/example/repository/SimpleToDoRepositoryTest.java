package org.example.repository;

import org.assertj.core.api.Assertions;
import org.example.model.ToDo;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class SimpleToDoRepositoryTest {

    @Test
    @DisplayName("SimpleToDoRepository Save Test")
    public void save(){
        ToDoRepository repository = new SimpleToDoRepository();
        ToDo toDo = new ToDo();

        ToDo save = repository.save(toDo);

        Assertions.assertThat(save.getToDo()).isEqualTo(toDo.getToDo());
    }

}
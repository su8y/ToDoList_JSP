package org.example.repository;

import org.example.model.ToDo;

import java.sql.SQLException;
import java.util.Optional;

public class JdbcToDoRepository extends JDBConnect implements ToDoRepository {

    @Override
    public int save(ToDo toDo) {
        int res;
        try {
            pstm = conn.prepareStatement("" +
                    "insert into todos(todo_id, m_id, todo, status) " +
                    "values(todos_seq.nextval,?,?,0");
            pstm.setString(1, toDo.getM_name());
            pstm.setString(2, toDo.getToDo());
            res = pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
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

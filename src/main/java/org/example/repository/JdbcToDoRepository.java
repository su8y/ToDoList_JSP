package org.example.repository;

import org.example.model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcToDoRepository implements ToDoRepository {
    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int save(ToDo toDo) {
        int res;
        try {
            pstmt = conn.prepareStatement("" +
                    "insert into todos(todo_id, m_id, todo, status) " +
                    "values(todos_seq.nextval,?,?,0");
            pstmt.setString(1, toDo.getM_id());
            pstmt.setString(2, toDo.getToDo());
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public List<ToDo> findAll() {
        List<ToDo> result;

        System.out.println("find all");
        try {
            pstmt = conn.prepareStatement("select * from TODOS");
            rs = pstmt.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                ToDo toDo = ToDo.builder()
                        .toDoId(rs.getLong("TODO_ID"))
                        .m_id(rs.getString("M_ID"))
                        .toDo(rs.getString("TODO"))
                        .status(rs.getInt("STATUS"))
                        .build();
                result.add(toDo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<ToDo> findToDosByUsername(String username) {
        List<ToDo> result;
        try {
            pstmt = conn.prepareStatement("select * from TODOS where M_ID = ? ORDER BY status ASC");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                ToDo toDo = ToDo.builder()
                        .toDoId(rs.getLong("TODO_ID"))
                        .m_id(rs.getString("M_ID"))
                        .toDo(rs.getString("TODO"))
                        .status(rs.getInt("STATUS"))
                        .build();
                result.add(toDo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result != null ? result : Collections.emptyList();
    }

    @Override
    public List<ToDo> findToDoById(long toDoId) {
        return null;
    }

    @Override
    public int delete(ToDo toDo) {
        return 0;
    }

    @Override
    public int updateToDo(ToDo toDo) {
        int result;
        try {
            pstmt = conn.prepareStatement("UPDATE TODOS set status = 1 where TODO_ID = ?");
            pstmt.setLong(1, toDo.getToDoId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

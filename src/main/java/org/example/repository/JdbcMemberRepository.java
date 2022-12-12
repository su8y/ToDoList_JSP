package org.example.repository;

import org.example.model.Member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcMemberRepository extends JDBConnect implements MemberRepository{
    @Override
    public Member save(Member member) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into member(m_id,m_pw,m_name,m_email) values(?,?,?,?)");
            preparedStatement.setString(1,member.getMId());
            preparedStatement.setString(2,member.getMPw());
            preparedStatement.setString(3,member.getMName());
            preparedStatement.setString(4,member.getMPw());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    @Override
    public Optional<Member> findAll(Member member) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(String mId) {
        return Optional.empty();
    }

    @Override
    public int delete(Member member) {
        int result;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from member where m_id = ? ");
            preparedStatement.setString(1,member.getMId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(Member member) {
        return 0;
    }
}

package org.example.repository;

import org.example.model.Member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class JdbcMemberRepository extends JDBConnect implements MemberRepository{
    @Override
    public Member save(Member member) {
        try {
            System.out.println("hi");
            System.out.println(member.getMId());
            PreparedStatement preparedStatement = conn.prepareStatement("insert into member(m_id,m_pw,m_name,m_email) values(?,?,?,?)");
            preparedStatement.setString(1,member.getMId());
            preparedStatement.setString(2,member.getMPw());
            preparedStatement.setString(3,member.getMName());
            preparedStatement.setString(4,member.getMPw());
            preparedStatement.executeUpdate();
            conn.commit();
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
    public Member findById(String mId) {
        Member member = new Member();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from member where m_id = ?");
            preparedStatement.setString(1,mId);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                member.builder().mPw(rs.getString("pw"));
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    @Override
    public int delete(Member member) {
        int result;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from member where m_id = ? ");
            preparedStatement.setString(1,member.getMId());
            result = preparedStatement.executeUpdate();
            conn.commit();
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

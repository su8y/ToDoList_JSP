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
    public Member save(Member member) throws RuntimeException{
        try {
            System.out.println("hi");
            System.out.println(member.getMId());
            pstm = conn.prepareStatement("insert into member(m_id,m_pw,m_name,m_email) values(?,?,?,?)");
            pstm.setString(1,member.getMId());
            pstm.setString(2,member.getMPw());
            pstm.setString(3,member.getMName());
            pstm.setString(4,member.getMPw());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return member;
    }

    @Override
    public Optional<Member> findAll(Member member) {
        return Optional.empty();
    }

    @Override
    public Member findById(String mId) throws RuntimeException {
        Member member = null;
        try {
            pstm = conn.prepareStatement("select * from member where m_id = ?");
            pstm.setString(1,mId);
            rs = pstm.executeQuery();
            if(rs.next()){
                String m_email = rs.getString("m_email");
                String m_name = rs.getString("m_name");
                String m_id = rs.getString("m_id");
                String m_pw = rs.getString("m_pw");
                member=  Member.builder().mId(m_id).mPw(m_pw).mEmail(m_email).mName(m_name).build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return member;
    }

    @Override
    public int delete(Member member) {
        int result;
        try {
            pstm = conn.prepareStatement("delete from member where m_id = ? ");
            pstm.setString(1,member.getMId());
            result = pstm.executeUpdate();
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

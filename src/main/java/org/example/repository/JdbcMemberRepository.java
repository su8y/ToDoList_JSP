package org.example.repository;

import org.example.model.Member;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{
    Connection conn;
    PreparedStatement pstmt;

    Statement stmt;

    ResultSet rs;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Member save(Member member) throws RuntimeException{
        try {
            pstmt = conn.prepareStatement("insert into member(m_id,m_pw,m_name,m_email) values(?,?,?,?)");
            pstmt.setString(1,member.getMId());
            pstmt.setString(2,member.getMPw());
            pstmt.setString(3,member.getMName());
            pstmt.setString(4,member.getMPw());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }

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
            pstmt = conn.prepareStatement("select * from member where m_id = ?");
            pstmt.setString(1,mId);
            rs = pstmt.executeQuery();

            if(rs.next()){
                String m_email = rs.getString("m_email");
                String m_name = rs.getString("m_name");
                String m_id = rs.getString("m_id");
                String m_pw = rs.getString("m_pw");
                member=  Member.builder().mId(m_id).mPw(m_pw).mEmail(m_email).mName(m_name).build();
            }else{
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }

        }
        return member;
    }

    @Override
    public int delete(Member member) {
        int result;
        try {
            pstmt = conn.prepareStatement("delete from member where m_id = ? ");
            pstmt.setString(1,member.getMId());
            result = pstmt.executeUpdate();
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

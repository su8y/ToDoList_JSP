package org.example.repository;

import org.example.model.Member;

import java.sql.SQLException;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findAll(Member member);
    Member findById(String mId) throws SQLException;
    int delete(Member member);
    int update(Member member);
}

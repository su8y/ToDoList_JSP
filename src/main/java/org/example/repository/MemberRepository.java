package org.example.repository;

import org.example.model.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findAll(Member member);
    Member findById(String mId);
    int delete(Member member);
    int update(Member member);
}

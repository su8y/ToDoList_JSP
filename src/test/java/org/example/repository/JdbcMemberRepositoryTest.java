package org.example.repository;

import org.assertj.core.api.Assertions;
import org.example.model.Member;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Spy;

public class JdbcMemberRepositoryTest {

    @Test
    @DisplayName("save Test")
    public void save() {
        MemberRepository repository = new JdbcMemberRepository();
        Member member = new Member();
        member.builder()
                .mId("qotndk530")
                .mPw("qotndk530")
                .mEmail("qotndk530@gmail.com")
                .mName("배수아");

        Member save = repository.save(member);

        Assertions.assertThat(save.getMName()).isEqualTo(member.getMName());
    }
}
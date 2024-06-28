package api.drunkhouse.service;

import api.drunkhouse.domain.Member;
import api.drunkhouse.dto.SignUpDto;
import api.drunkhouse.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void loginTest() throws Exception {
        //given
        Member member = new Member("test", "1111");
        em.persist(member);

        //when
        Member findMember = memberService.login(member.getUserName(), member.getPassword());

        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    @Rollback(false)
    public void signUpTest() throws Exception {
        //given
        SignUpDto member1 = new SignUpDto("test", "1111");
        SignUpDto member2 = new SignUpDto("test", "11112");

        //when
        int row1 = memberService.signUp(member1);
        int row2 = memberService.signUp(member2);

        //then
        assertThat(row1).isEqualTo(1);
        assertThat(row2).isEqualTo(0);

    }

}
package api.drunkhouse.service;

import api.drunkhouse.domain.Member;
import api.drunkhouse.dto.SignUpDto;
import api.drunkhouse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member login(String userName, String password) {
        return memberRepository.findByUserNameAndPassword(userName, password);
    }

    public int signUp(SignUpDto memberDto) {
        if (memberRepository.existsByUserName(memberDto.getUserName())) {
            return 0;
        }
        Member member = new Member();
        member = member.setMemberToSignUpDto(memberDto);
        memberRepository.save(member);
        return 1;
    }
}

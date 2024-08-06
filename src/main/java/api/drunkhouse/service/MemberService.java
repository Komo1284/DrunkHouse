package api.drunkhouse.service;

import api.drunkhouse.domain.Member;
import api.drunkhouse.dto.MemberDto;
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

    public MemberDto login(String userName, String password) {
        Member findMember = memberRepository.findByUserNameAndPassword(userName, password);

        return new MemberDto(findMember.getId(),
                findMember.getUserName(),
                findMember.getNick(),
                findMember.getGender(),
                findMember.getBirth());
    }

    public int signUp(SignUpDto dto) {
        if (memberRepository.existsByUserName(dto.getUserName())) {
            return 0;
        }
        Member member = dto.setMember();
        memberRepository.save(member);
        return 1;
    }
}

package api.drunkhouse.controller;

import api.drunkhouse.domain.Member;
import api.drunkhouse.dto.LoginDto;
import api.drunkhouse.dto.SignUpDto;
import api.drunkhouse.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody LoginDto member) {
        Member findMember = memberService.login(member.getUserName(), member.getPassword());
        if (findMember != null) {
            return new ResponseEntity<>(findMember, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(@RequestBody SignUpDto memberDto) {
        int row = memberService.signUp(memberDto);
        if (row != 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

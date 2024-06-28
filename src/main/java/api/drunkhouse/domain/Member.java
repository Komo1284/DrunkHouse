package api.drunkhouse.domain;

import api.drunkhouse.dto.SignUpDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String userName;

    private String password;

    private String nick;

    private Gender gender;

    private String birth;

    public Member(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Member setMemberToSignUpDto(SignUpDto dto){
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.nick = dto.getNick();
        this.gender = dto.getGender();
        this.birth = dto.getBirth();
        return this;
    }
}

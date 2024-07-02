package api.drunkhouse.dto;

import api.drunkhouse.domain.Gender;
import api.drunkhouse.domain.Member;
import lombok.Data;

@Data
public class SignUpDto {

    private String userName;
    private String password;
    private String nick;
    private Gender gender;
    private String birth;

    public SignUpDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Member setMember() {
        return new Member(
                userName,
                password,
                nick,
                gender,
                birth
        );
    }
}

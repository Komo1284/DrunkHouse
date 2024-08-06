package api.drunkhouse.dto;

import api.drunkhouse.domain.Gender;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String userName;
    private String nick;
    private Gender gender;
    private String birth;

    public MemberDto(Long id, String userName, String nick, Gender gender, String birth) {
        this.id = id;
        this.userName = userName;
        this.nick = nick;
        this.gender = gender;
        this.birth = birth;
    }
}

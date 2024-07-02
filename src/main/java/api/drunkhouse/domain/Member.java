package api.drunkhouse.domain;

import api.drunkhouse.dto.SignUpDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String userName;

    private String password;

    private String nick;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String birth;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Member(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Member(String userName, String password, String nick, Gender gender, String birth) {
        this.userName = userName;
        this.password = password;
        this.nick = nick;
        this.gender = gender;
        this.birth = birth;
    }
}

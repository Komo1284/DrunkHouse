package api.drunkhouse.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long id;

    private String name;
    private int abv;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    private String profile;

    @Enumerated(EnumType.STRING)
    private Category category;

}

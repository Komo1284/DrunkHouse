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

    @Enumerated(EnumType.STRING)
    private Hide hide = Hide.HIDE;

    public Drink(String name, int abv, String content, String profile, Category category) {
        this.name = name;
        this.abv = abv;
        this.content = content;
        this.profile = profile;
        this.category = category;
    }

    public void showDrink() {
        this.hide = Hide.SHOW;
    }
}

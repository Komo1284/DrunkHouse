package api.drunkhouse.domain;

import api.drunkhouse.dto.DrinkUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime hideDate;  // 마지막으로 hide된 날짜

    public Drink(String name, int abv, String content, String profile, Category category) {
        this.name = name;
        this.abv = abv;
        this.content = content;
        this.profile = profile;
        this.category = category;
    }

    public void showDrink() {
        this.hide = Hide.SHOW;
        this.hideDate = null;   // 다시 등록된 경우 삭제되지 않게 하기위해 삭제날짜 null
    }

    public void update(DrinkUpdateDto dto) {
        this.name = dto.getName();
        this.abv = dto.getAbv();
        this.content = dto.getContent();
        this.profile = dto.getProfile();
        this.category = dto.getCategory();
    }

    public void hideDrink() {
        this.hide = Hide.HIDE;
        this.hideDate = LocalDateTime.now();  // 현재 날짜와 시간 기록
    }
}

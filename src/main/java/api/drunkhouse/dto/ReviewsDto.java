package api.drunkhouse.dto;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.domain.Review;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ReviewsDto {

    private Long id;
    private String drinkName;
    private int drinkAbv;
    private String title;
    private String content;

    public ReviewsDto(Long id, String drinkName, int drinkAbv, String title, String content) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkAbv = drinkAbv;
        this.title = title;
        this.content = content;
    }
}

package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class DrinkViewDto {

    private Long id;
    private String name;
    private int abv;
    private String content;
    private String profile;
    private Category category;

    public DrinkViewDto(Long id, String name, int abv, String content, String profile, Category category) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.content = content;
        this.profile = profile;
        this.category = category;
    }
}

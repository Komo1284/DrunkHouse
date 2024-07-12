package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class DrinkListDto {

    private Long id;
    private String name;
    private Category category;
    private String profile;
    private int abv;

    @QueryProjection
    public DrinkListDto(Long id, String name, Category category, String profile, int abv) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.profile = profile;
        this.abv = abv;
    }
}

package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import lombok.Data;

@Data
public class DrinkUpdateDto {

    private String name;
    private int abv;
    private String content;
    private String profile;
    private Category category;
}

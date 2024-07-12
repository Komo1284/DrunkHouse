package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import lombok.Data;

@Data
public class DrinkSearchCondition {

    private Category category;
    private String keyword;
}

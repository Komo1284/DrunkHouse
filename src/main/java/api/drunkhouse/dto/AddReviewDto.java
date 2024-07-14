package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class AddReviewDto {

    private Long memberId;
    private Long drinkId;
    private String title;
    private String content;

    private String drinkName;
    private Integer drinkAbv;
    private String drinkContent;
    private Category drinkCategory;

}

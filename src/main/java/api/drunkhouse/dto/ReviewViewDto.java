package api.drunkhouse.dto;

import api.drunkhouse.domain.Category;
import api.drunkhouse.domain.Review;
import lombok.Data;

@Data
public class ReviewViewDto {

    private Long reviewId;
    private String drinkName;
    private int drinkAbv;
    private String drinkProfile;
    private Category category;
    private String title;
    private String content;


    public ReviewViewDto(Review review) {
        this.reviewId = review.getId();
        this.drinkName = review.getDrink().getName();
        this.drinkAbv = review.getDrink().getAbv();
        this.drinkProfile = review.getDrink().getProfile();
        this.category = review.getDrink().getCategory();
        this.title = review.getTitle();
        this.content = review.getContent();
    }
}

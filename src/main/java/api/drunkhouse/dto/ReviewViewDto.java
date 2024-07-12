package api.drunkhouse.dto;

import lombok.Data;

@Data
public class ReviewViewDto {

    private Long id;
    private String drinkName;
    private int drinkAbv;
    private String title;
    private String content;

    public ReviewViewDto(Long id, String drinkName, int drinkAbv, String title, String content) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkAbv = drinkAbv;
        this.title = title;
        this.content = content;
    }
}

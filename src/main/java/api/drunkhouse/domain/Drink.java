package api.drunkhouse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Drink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long id;

    private String name;
    private int abv;
    private int bottleSize;
    private int price;
    private String content;
    private Category category;

    @OneToMany(mappedBy = "drink")
    private List<Review> reviews = new ArrayList<>();

}

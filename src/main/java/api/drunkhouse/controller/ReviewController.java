package api.drunkhouse.controller;

import api.drunkhouse.domain.Member;
import api.drunkhouse.domain.Review;
import api.drunkhouse.dto.*;
import api.drunkhouse.service.DrinkService;
import api.drunkhouse.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final DrinkService drinkService;

    @GetMapping("/{memberId}")
    public Map<String, Object> getReviews(@PathVariable("memberId") Long memberId, DrinkSearchCondition condition,
                                          @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<DrinkListDto> result = reviewService.searchReviews(memberId, condition, pageable);
        map.put("reviews", result.getContent());
        map.put("page", result.getNumber());
        map.put("condition", condition);

        return map;
    }

    @PostMapping
    public ResponseEntity<Review> AddReview(AddReviewDto dto, MultipartFile file) throws IOException {
        if (dto.getDrinkId() == null) {
            dto.setDrinkId(drinkService.AddDrink(dto, file));
        }

        System.out.println(dto.getDrinkId());
        reviewService.AddReview(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public String UpdateReview(@PathVariable("id") Long id) {
        return "";
    }

    @DeleteMapping("/{id}")
    public String DeleteReview(@PathVariable("id") Long id) {
        return  "";
    }

}

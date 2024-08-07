package api.drunkhouse.controller;

import api.drunkhouse.domain.Member;
import api.drunkhouse.domain.Review;
import api.drunkhouse.dto.*;
import api.drunkhouse.repository.ReviewRepository;
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
    private final ReviewRepository reviewRepository;

    @GetMapping
    public Map<String, Object> getReviews(@RequestParam Long memberId, DrinkSearchCondition condition,
                                          @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<DrinkListDto> result = reviewService.searchReviews(memberId, condition, pageable);
        map.put("reviews", result.getContent());
        map.put("page", result.getNumber());
        map.put("condition", condition);

        return map;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewViewDto> getReview(@PathVariable("reviewId") Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PostMapping
    public ResponseEntity<Review> AddReview(AddReviewDto dto, @RequestParam(required = false) MultipartFile file) throws IOException {
        if (dto.getDrinkId() == null) {
            dto.setDrinkId(drinkService.AddDrink(dto, file));
        }

        reviewService.AddReview(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> UpdateReview(@PathVariable("reviewId") Long reviewId, UpdateReviewDto dto) {
        reviewService.update(reviewId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Review> DeleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

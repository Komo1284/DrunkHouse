package api.drunkhouse.controller;

import api.drunkhouse.domain.Review;
import api.drunkhouse.dto.ReviewsDto;
import api.drunkhouse.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public List<ReviewsDto> getReviews(@PathVariable("id") Long id) {
        return reviewService.getMyReviews(id);
    }
}

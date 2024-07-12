package api.drunkhouse.controller;

import api.drunkhouse.dto.*;
import api.drunkhouse.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public Map<String, Object> getReviews(@PathVariable("id") Long id, DrinkSearchCondition condition,
                                          @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<DrinkListDto> result = reviewService.searchReviews(id, condition, pageable);
        map.put("reviews", result.getContent());
        map.put("page", result.getNumber());
        map.put("condition", condition);

        return map;
    }

}

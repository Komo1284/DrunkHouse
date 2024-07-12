package api.drunkhouse.service;

import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.ReviewSearchCondition;
import api.drunkhouse.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<DrinkListDto> searchReviews(Long id, DrinkSearchCondition condition, Pageable pageable) {
        return reviewRepository.searchReviews(id, condition, pageable);
    }
}

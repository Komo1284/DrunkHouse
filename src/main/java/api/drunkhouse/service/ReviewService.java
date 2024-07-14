package api.drunkhouse.service;

import api.drunkhouse.domain.Review;
import api.drunkhouse.dto.AddReviewDto;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.ReviewSearchCondition;
import api.drunkhouse.repository.DrinkRepository;
import api.drunkhouse.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final DrinkRepository drinkRepository;

    public Page<DrinkListDto> searchReviews(Long id, DrinkSearchCondition condition, Pageable pageable) {
        return reviewRepository.searchReviews(id, condition, pageable);
    }

    public void AddReview(AddReviewDto dto) {
        reviewRepository.save(
                new Review(
                        memberRepository.findById(dto.getMemberId()).get(),
                        drinkRepository.findById(dto.getDrinkId()).get(),
                        dto.getTitle(),
                        dto.getContent()
                )
        );
    }
}

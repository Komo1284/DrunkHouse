package api.drunkhouse.service;

import api.drunkhouse.domain.Review;
import api.drunkhouse.dto.ReviewsDto;
import api.drunkhouse.repository.MemberRepository;
import api.drunkhouse.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final MemberRepository memberRepository;

    public List<ReviewsDto> getMyReviews(Long id) {
        List<Review> reviews = memberRepository.findById(id).get().getReviews();
        List<ReviewsDto> result = reviews.stream()
                .map(review -> new ReviewsDto(review.getId(), review.getDrink().getName(), review.getDrink().getAbv(), review.getTitle(), review.getContent()))
                .collect(Collectors.toList());
        System.out.println("result = " + result);
        return result;
    }
}

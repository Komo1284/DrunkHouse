package api.drunkhouse.repository;

import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.ReviewSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<DrinkListDto> searchReviews(Long id, DrinkSearchCondition condition, Pageable pageable);
}

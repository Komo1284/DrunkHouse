package api.drunkhouse.repository;

import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DrinkRepositoryCustom {
    Page<DrinkListDto> searchDrinks(DrinkSearchCondition condition, Pageable pageable);
}

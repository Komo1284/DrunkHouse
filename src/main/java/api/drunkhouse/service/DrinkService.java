package api.drunkhouse.service;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.DrinkViewDto;
import api.drunkhouse.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public Page<DrinkListDto> searchDrinks(DrinkSearchCondition condition, Pageable pageable) {
        return drinkRepository.searchDrinks(condition, pageable);
    }

    public DrinkViewDto getDrink(Long id) {
        Drink drink = drinkRepository.findById(id).get();
        return new DrinkViewDto(
                drink.getId(),
                drink.getName(),
                drink.getAbv(),
                drink.getContent(),
                drink.getProfile(),
                drink.getCategory()
        );
    }
}

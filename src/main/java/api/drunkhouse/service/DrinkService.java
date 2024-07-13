package api.drunkhouse.service;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.dto.AddReviewDto;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.DrinkViewDto;
import api.drunkhouse.repository.DrinkRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final ImageService imageService;
    private final EntityManager entityManager;

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

    public Long AddDrink(AddReviewDto dto, MultipartFile file) throws IOException {
        Drink savedDrink = drinkRepository.save(new Drink(
                dto.getDrinkName(),
                dto.getDrinkAbv(),
                dto.getDrinkContent(),
                imageService.imageUploadFromFile(file),
                dto.getDrinkCategory()
        ));

        entityManager.flush();
        entityManager.clear();

        return savedDrink.getId();
    }
}

package api.drunkhouse.service;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.domain.Hide;
import api.drunkhouse.dto.*;
import api.drunkhouse.repository.DrinkRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Object> addDrink(Long drinkId) {
        Drink drink = drinkRepository.findById(drinkId).orElse(null);
        if (drink == null) {
            ErrorResponse errorResponse = new ErrorResponse("0001", "Drink not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else if (drink.getHide() == Hide.SHOW) {
            ErrorResponse errorResponse = new ErrorResponse("0002", "Drink is already visible");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        drink.showDrink();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> update(Long drinkId, DrinkUpdateDto dto, MultipartFile file) throws IOException {
        Drink drink = drinkRepository.findById(drinkId).orElse(null);
        if (drink == null) {
            ErrorResponse errorResponse = new ErrorResponse("0001", "Drink not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        dto.setProfile(imageService.imageUploadFromFile(file));
        drink.update(dto);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<Object> delete(Long drinkId) {
        Drink drink = drinkRepository.findById(drinkId).orElse(null);
        if (drink == null) {
            ErrorResponse errorResponse = new ErrorResponse("0001", "Drink not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else if (drink.getHide() == Hide.HIDE) {
            ErrorResponse errorResponse = new ErrorResponse("0002", "Drink is already delete");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        drink.hideDrink();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

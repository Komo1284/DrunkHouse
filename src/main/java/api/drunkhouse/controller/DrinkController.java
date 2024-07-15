package api.drunkhouse.controller;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.DrinkUpdateDto;
import api.drunkhouse.dto.DrinkViewDto;
import api.drunkhouse.repository.DrinkRepository;
import api.drunkhouse.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drink")
public class DrinkController {

    private final DrinkRepository drinkRepository;
    private final DrinkService drinkService;

    @GetMapping
    public Map<String, Object> getDrinks(DrinkSearchCondition condition,
                                        @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<DrinkListDto> result = drinkService.searchDrinks(condition, pageable);
        map.put("drinks", result.getContent());
        map.put("page", result.getNumber());
        map.put("condition", condition);

        return map;
    }

    @GetMapping("/{drinkId}")
    public DrinkViewDto getDrink(@PathVariable Long drinkId) {
        return drinkService.getDrink(drinkId);
    }

    @PostMapping
    public ResponseEntity<Object> addDrink(@RequestParam Long drinkId) {
        return drinkService.addDrink(drinkId);
    }

    @PutMapping("/{drinkId}")
    public ResponseEntity<Object> updateDrink(@PathVariable Long drinkId, DrinkUpdateDto dto, MultipartFile file) throws IOException {
        return drinkService.update(drinkId, dto, file);
    }

    @DeleteMapping("/{drinkId}")
    public ResponseEntity<Object> deleteDrink(@PathVariable Long drinkId) {
        return drinkService.delete(drinkId);
    }

}

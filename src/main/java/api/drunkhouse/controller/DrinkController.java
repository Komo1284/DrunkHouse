package api.drunkhouse.controller;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.DrinkViewDto;
import api.drunkhouse.repository.DrinkRepository;
import api.drunkhouse.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public DrinkViewDto getDrink(@PathVariable Long id) {
        return drinkService.getDrink(id);
    }

}

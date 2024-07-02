package api.drunkhouse.controller;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drink")
public class DrinkController {

    private final DrinkRepository drinkRepository;

    @GetMapping
    public List<Drink> getDrink() {
        return drinkRepository.findAll();
    }
}

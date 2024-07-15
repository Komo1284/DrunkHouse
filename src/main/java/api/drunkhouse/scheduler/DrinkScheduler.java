package api.drunkhouse.scheduler;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.domain.Hide;
import api.drunkhouse.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class DrinkScheduler {

    private final DrinkRepository drinkRepository;

    @Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
    public void deleteOldHiddenDrinks() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<Drink> drinksToDelete = drinkRepository.findAllByHideAndHideDateBefore(Hide.HIDE, oneMonthAgo);
        drinkRepository.deleteAll(drinksToDelete);
    }
}
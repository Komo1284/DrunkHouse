package api.drunkhouse.repository;

import api.drunkhouse.domain.Drink;
import api.drunkhouse.domain.Hide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> , DrinkRepositoryCustom {
    List<Drink> findAllByHideAndHideDateBefore(Hide hide, LocalDateTime oneMonthAgo);
}

package api.drunkhouse.repository;

import api.drunkhouse.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> , DrinkRepositoryCustom {
}

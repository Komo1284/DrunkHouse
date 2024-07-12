package api.drunkhouse.repository;

import api.drunkhouse.domain.Category;
import api.drunkhouse.domain.Drink;
import api.drunkhouse.domain.QDrink;
import api.drunkhouse.dto.DrinkListDto;
import api.drunkhouse.dto.DrinkSearchCondition;
import api.drunkhouse.dto.QDrinkListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static api.drunkhouse.domain.QDrink.*;

public class DrinkRepositoryImpl implements DrinkRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public DrinkRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<DrinkListDto> searchDrinks(DrinkSearchCondition condition, Pageable pageable) {
        List<DrinkListDto> content = queryFactory
                .select(new QDrinkListDto(
                        drink.id,
                        drink.name,
                        drink.category,
                        drink.profile,
                        drink.abv
                ))
                .from(drink)
                .where(
                        selectDrinkType(condition),
                        Search(condition)
                )
                .orderBy(drink.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(drink.count())
                .from(drink)
                .where(
                        selectDrinkType(condition),
                        Search(condition)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression selectDrinkType(DrinkSearchCondition condition) {
        if (condition.getCategory() != null) {
            switch (condition.getCategory()) {
                case BEER:
                    return drink.category.eq(Category.BEER);
                case BRANDY:
                    return drink.category.eq(Category.BRANDY);
                case RUM:
                    return drink.category.eq(Category.RUM);
                case SAKE:
                    return drink.category.eq(Category.SAKE);
                case MAKGEOLLI:
                    return drink.category.eq(Category.MAKGEOLLI);
                case SHOCHU:
                    return drink.category.eq(Category.SHOCHU);
                case VODKA:
                    return drink.category.eq(Category.VODKA);
                case WHISKY:
                    return drink.category.eq(Category.WHISKY);
                case WINE:
                    return drink.category.eq(Category.WINE);
                default:
                    break;
            }
        }
        // 기본적으로 모든 타입을 포함하도록 설정
        return drink.category.in(Category.BEER, Category.BRANDY, Category.RUM, Category.SAKE, Category.MAKGEOLLI, Category.SHOCHU, Category.WINE, Category.WHISKY, Category.VODKA);
    }

    private BooleanExpression Search(DrinkSearchCondition condition) {
        if (condition.getKeyword() == null) {
            return null;
        }
        return drink.name.contains(condition.getKeyword());
    }
}

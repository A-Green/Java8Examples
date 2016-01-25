package streams.collector;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import streams.CaloricLevel;
import streams.Dish;
import streams.MenuBased;
import streams.Dish.Type;

public class Grouping extends MenuBased {

	public static void main(String[] args) {

		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> determineCaloricLevel(dish.getCalories()) )));
		System.out.println(dishesByTypeCaloricLevel);

		Map<Dish.Type, Long> countByType = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println(countByType);

		Map<Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(
				Dish::getType, 
				maxBy(comparingInt(Dish::getCalories))));
		System.out.println(mostCaloricByType);

		//eliminate Optional
		Map<Dish.Type, Dish> mostCaloricByTypeWithoutOptional =
				menu.stream()
				.collect(groupingBy(Dish::getType,
					collectingAndThen(
						maxBy(comparingInt(Dish::getCalories)),
						Optional::get)));
		System.out.println(mostCaloricByTypeWithoutOptional);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(
						dish -> determineCaloricLevel(dish.getCalories()) , toSet())));
		System.out.println(caloricLevelsByType);

		//Using toCollection method
		caloricLevelsByType =
				menu.stream().collect(
				groupingBy(Dish::getType, mapping(
						dish -> determineCaloricLevel(dish.getCalories()) , toCollection(HashSet::new) )));

		System.out.println(caloricLevelsByType);
	}

	private static CaloricLevel determineCaloricLevel(long calories) {
		if (calories <= 400) 
			return CaloricLevel.DIET;
		else if (calories <= 700) 
			return CaloricLevel.NORMAL;
		else 
			return CaloricLevel.FAT; 
	}
}

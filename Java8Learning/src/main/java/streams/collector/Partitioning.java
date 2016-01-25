package streams.collector;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import streams.Dish;
import streams.MenuBased;

public class Partitioning extends MenuBased {

	public static void main(String[] args) {
		Map<Boolean, List<Dish>> partitionedMenu =
				menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(partitionedMenu);
		
		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = 
				menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
		System.out.println(vegetarianDishesByType);
		
		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
				menu.stream().collect(
						partitioningBy(Dish::isVegetarian,
									collectingAndThen(
											maxBy(comparingInt(Dish::getCalories)),
											Optional::get)));
		System.out.println(mostCaloricPartitionedByVegetarian);
		
		Map<Boolean, Long> countByVegeterian = 
				menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
		System.out.println(countByVegeterian);

		//custom collector usage example
		List<Dish> dishes = menu.stream().collect(new CustomToListCollector<Dish>());
		System.out.println(dishes);
	}

}

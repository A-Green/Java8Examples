package practice.streams;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

	private static List<Transaction> transactions;

	static {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950));
	}

	public static void main(String[] args) {
		task1();
		task2();
		task3();
		task4();
		task5();
		task6();
		task7();
		task8();
	}

	public static void task1() {
		//Find all transactions in the year 2011 and sort them by value (small to high).
		List<Transaction> answer = transactions.stream()
											.filter(t -> t.getYear() == 2012)
											.sorted(Comparator.comparing(Transaction::getValue))
											.collect(toList());
		System.out.println(answer);
	}

	public static void task2() {
		//What are all the unique cities where the traders work?
		List<String> cities = transactions.stream()
										.map(t -> t.getTrader().getCity())
										.distinct()
										.collect(toList());
		System.out.println(cities);
	}

	public static void task3() {
		//Find all traders from Cambridge and sort them by name
		List<Trader> traders = transactions.stream()
										.map(Transaction::getTrader)
										.filter(t -> "Cambridge".equals(t.getCity()))
										.distinct()
										.sorted(Comparator.comparing(Trader::getName))
										.collect(toList());
		System.out.println(traders);
	}

	public static void task4() {
		//Return a string of all traders’ names sorted alphabetically.
		String concatenatedTraderNames = transactions.stream()
												.map(t -> t.getTrader().getName())
												.distinct()
												.sorted()
												.reduce("", String::concat);
		System.out.println(concatenatedTraderNames);
	}

	public static void task5()	{
		//Are any traders based in Milan?
		if (transactions.stream()
				.anyMatch(t -> "Milan".equals(t.getTrader().getCity()))) {
			System.out.println("There is traders based in Milan");
		}
	}

	public static void task6() {
		//Print all transactions’ values from the traders living in Cambridge.
		transactions.stream()
					.filter(t -> t.getTrader().getCity().equals("Cambridge"))
					.map(Transaction::getValue)
					.distinct()
					.forEach(System.out::println);
	}

	public static void task7() {
		//What’s the highest value of all the transactions?
		transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max)
				.ifPresent(System.out::println);
	}

	public static void task8() {
		// Find the transaction with the smallest value.
		transactions.stream()
					.min(Comparator.comparing(Transaction::getValue))
					.ifPresent(System.out::println);
	}
}
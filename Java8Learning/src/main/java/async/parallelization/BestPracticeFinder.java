package async.parallelization;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import async.parallelization.shop.SimpleShop;
import async.parallelization.shop.impl.DiscountlessShop;

public class BestPracticeFinder {

	private static List<SimpleShop> shops = Arrays.asList(new DiscountlessShop("BestPrice"),
													new DiscountlessShop("LetsSaveBig"),
													new DiscountlessShop("MyFavoriteShop"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("BuyItAll"),
													new DiscountlessShop("NotebookTable"));

	//create thread pool 
	private final Executor executor = Executors.newFixedThreadPool(
			Math.min(shops.size(), 100), new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
	});

	public List<String> findBySingleStream(String product) {
		return shops.stream()
				.map(shop -> shop.representPrice(product))
				.collect(toList());
	}

	public List<String> findByParallelStream(String product) {
		return shops.parallelStream()
				.map(shop -> shop.representPrice(product))
				.collect(toList());
	}

	public List<String> findByFutureAndSeveralStreams(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() ->  shop.representPrice(product), executor))
				.collect(Collectors.toList());

		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(toList());
	}

	public static void main(String[] args) {
		BestPracticeFinder bestPracticeFinder = new BestPracticeFinder();
		long start = System.nanoTime();
		System.out.println(bestPracticeFinder.findBySingleStream("MyPhone"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(bestPracticeFinder.findByParallelStream("MyPhone"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(bestPracticeFinder.findByFutureAndSeveralStreams("MyPhone"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}

}

package async.pipeline;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import async.parallelization.shop.SimpleShop;

public class Main {

	private static List<SimpleShop> shops = Arrays.asList(new DiscountUsingShop("BestPrice"),
														new DiscountUsingShop("LetsSaveBig"),
														new DiscountUsingShop("MyFavoriteShop"),
														new DiscountUsingShop("BuyItAll"),
														new DiscountUsingShop("NotebookTable"));

	//create thread pool 
	private final Executor executor = Executors.newFixedThreadPool(
			Math.min(shops.size(), 100), new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
	});

	public static void main(String[] args) {
		Main main = new Main();
		long start = System.nanoTime();
		System.out.println(main.findBySingleStream("MyPhone"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(main.findByparallelStream("MyPhone"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

		start = System.nanoTime();
		System.out.println(main.findByFutureAndSeveralStreams("MyPhone"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");

	}

	public List<String> findBySingleStream(String product) {
		return shops.stream()
			.map(shop -> shop.representPrice(product))
			.map(Quote::parse)
			.map(Discount::applyDiscount)
			.collect(toList());
	}

	public List<String> findByparallelStream(String product) {
		return shops.parallelStream()
			.map(shop -> shop.representPrice(product))
			.map(Quote::parse)
			.map(Discount::applyDiscount)
			.collect(toList());
	}

	public List<String> findByFutureAndSeveralStreams(String product) {	
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() ->  shop.representPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
				.collect(toList());
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(toList());
	}
}

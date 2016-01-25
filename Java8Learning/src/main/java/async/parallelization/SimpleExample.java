package async.parallelization;

import java.util.concurrent.Future;

import async.parallelization.shop.impl.DiscountlessShop;

public class SimpleExample {
	public static void main(String[] args) {
		DiscountlessShop shop = new DiscountlessShop("new Shop");
		long start = System.nanoTime();
		Future<String> futurePrice = shop.representPriceAsync("MyPhone");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		// Do some more tasks, like querying other shops
		doSomethingElse();
		// while the price of the product is being calculated
		try {
			String price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	private static void doSomethingElse() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

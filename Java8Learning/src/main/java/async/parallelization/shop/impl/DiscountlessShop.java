package async.parallelization.shop.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import async.parallelization.shop.AbstractShop;
import async.parallelization.shop.AsyncShop;
import async.parallelization.shop.SimpleShop;

public class DiscountlessShop extends AbstractShop implements SimpleShop, AsyncShop {

	public DiscountlessShop(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Future<String> representPriceAsync(String product) {
		CompletableFuture<String> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				String price = representPrice(product);
				futurePrice.complete(price);
			} catch (Exception e) {
				// will able to get exception in case of some troubles
				futurePrice.completeExceptionally(e);
			}
		}).start();
		return futurePrice;
	}

	@Override
	public String representPrice(String product) {
		return String.format("%s best price %.2f", name, getPrice(product));
	}

	public Future<String> oneLineSolutionGetPriceAsync(String product) {
		return CompletableFuture.supplyAsync(() -> representPrice(product));
	}

}

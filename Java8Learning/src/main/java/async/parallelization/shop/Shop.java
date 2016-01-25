package async.parallelization.shop;

import java.util.Random;

public interface Shop {

	default double getPrice(String productName) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Random().nextDouble();
	}
}

package async.parallelization.shop;

import java.util.concurrent.Future;

public interface AsyncShop extends Shop {
	Future<String> representPriceAsync(String poduct);
}

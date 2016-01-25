package async.pipeline;

import java.util.Random;

import async.parallelization.shop.AbstractShop;
import async.parallelization.shop.SimpleShop;

public class DiscountUsingShop extends AbstractShop implements SimpleShop {
	
	public DiscountUsingShop(String name) {
		this.name = name;
	}

	@Override
	public String representPrice(String product) {
		double price = getPrice(product);
		Discount.Code code = Discount.Code.values()[ new Random().nextInt(Discount.Code.values().length) ];
		return String.format("%s:%.2f:%s", name, price, code);
	}

}

package patterns.templatemethod;

import java.util.function.Consumer;

public class OnlineBankingLambda {

	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy.accept(c);
	}
	
	public static void main(String[] args) {
		//usage example
		new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));
	}

}

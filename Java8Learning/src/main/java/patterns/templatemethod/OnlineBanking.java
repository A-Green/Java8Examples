package patterns.templatemethod;

public abstract class OnlineBanking {

	public void processCustomer(int id) {
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy(c);
	}

	// inherit and override makeCustomerHappy method
	abstract void makeCustomerHappy(Customer c);
}

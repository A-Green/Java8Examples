package patterns.templatemethod;

public class Database {

	public static Customer getCustomerWithId(int id) {
		return new Customer("username");
	}

}

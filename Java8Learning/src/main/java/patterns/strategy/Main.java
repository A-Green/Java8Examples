package patterns.strategy;

public class Main {

	public static void main(String[] args) {
		oldUsageExample();

		newUsageExample();

	}

	private static void oldUsageExample() {
		Validator numericValidator = new Validator(new IsNumeric());
		boolean b1 = numericValidator.validate("aaaa");
		System.out.println(b1);
		Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		boolean b2 = lowerCaseValidator.validate("bbbb");
		System.out.println(b2);

	}

	//no inheritance at all, but if there is only one place where the strategy is used
	private static void newUsageExample() {
		Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
		boolean b1 = numericValidator.validate("aaaa");
		System.out.println(b1);
		Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
		boolean b2 = lowerCaseValidator.validate("bbbb");
		System.out.println(b2);
	}
}

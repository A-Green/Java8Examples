package patterns.strategy;

public class Validator {

	private ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s) {
		return strategy.validate(s);
	}

}

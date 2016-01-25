package optional.domain;

import java.util.Optional;

public class Person {

	private Optional<Car> car;

	public Person() {
		this.car = Optional.empty();
	}

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Optional<Car> car) {
		this.car = car;
	}

}

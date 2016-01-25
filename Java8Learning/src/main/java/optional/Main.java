package optional;

import java.util.Optional;

import optional.domain.Car;
import optional.domain.Insurance;
import optional.domain.Person;

public class Main {

	public static void main(String[] args) {

		tarverseNullableObjectExample();

		//nullSafeFindCheapestInsurance();

		getFieldFromNullable();
	}

	private static void tarverseNullableObjectExample() {
		Optional<Person> optPerson = Optional.ofNullable(new Person());
		String name = optPerson.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("UNKNOWN");

		System.out.println(name);

	}

/*	private static void nullSafeFindCheapestInsurance() {

		Optional<Car> car = Optional.of(new Car());
		Optional<Person> person = Optional.of(new Person());

		Insurance insrance = person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}

	private static Insurance findCheapestInsurance(Person p, Car c) {
		return new Insurance();
	}*/

	private static void getFieldFromNullable() {
		Optional<Insurance> optInsurance = Optional.ofNullable(null);
		optInsurance.filter(insurance ->
								"CambridgeInsurance".equals(insurance.getName()))
								.ifPresent(x -> System.out.println("ok"));
	}


}

package optional.domain;

import java.util.Optional;

public class Car {

	private Optional<Insurance> insurance;

	public Car() {
		this.insurance = Optional.empty();
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Optional<Insurance> isurance) {
		this.insurance = isurance;
	}

}

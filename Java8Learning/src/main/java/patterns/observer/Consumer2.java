package patterns.observer;

public class Consumer2 implements Observer {

	@Override
	public void notify(String s) {
		if (s != null && s.contains("cars")) {
			System.out.println("Consumed by consumer 2");
		}
	}
}

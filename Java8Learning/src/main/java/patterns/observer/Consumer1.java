package patterns.observer;

public class Consumer1 implements Observer {

	@Override
	public void notify(String s) {
		if (s != null && s.contains("girls")) {
			System.out.println("Consumed by consumer 1");
		}
	}
}

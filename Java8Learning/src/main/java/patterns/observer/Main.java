package patterns.observer;

public class Main {
	public static void main(String[] args) {

		String tweet = "cars and girls";

		noLambdaExample(tweet);

		lambdaBasedExample(tweet);
	}

	private static void noLambdaExample(String tweet) {
		Feed subject = new Feed();
		subject.registerObserver(new Consumer1());
		subject.registerObserver(new Consumer2());
		subject.notifyObservers(tweet);
	}

	// java 8 example using lambdas. No need to inherit
	private static void lambdaBasedExample(String tweet) {
		Feed subject = new Feed();
		subject.registerObserver((String s) -> {
			if (s != null && s.contains("cars")) {
				System.out.println("Consumed by consumer 1 using lambda");
			}
		});

		subject.registerObserver((String s) -> {
			if (s != null && s.contains("girls")) {
				System.out.println("Consumed by consumer 2 using lambda");
			}
		});

		subject.notifyObservers(tweet);
	}
}

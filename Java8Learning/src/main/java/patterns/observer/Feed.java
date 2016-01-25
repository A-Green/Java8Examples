package patterns.observer;

import java.util.HashSet;
import java.util.Set;

public class Feed implements Subject {

	private Set<Observer> observers = new HashSet<>();

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers(String s) {
		observers.forEach(o -> o.notify(s));
	}
}

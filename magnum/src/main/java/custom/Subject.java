package custom;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<MyObserver> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public void registerObserver(MyObserver observer) {
        observers.add(observer);
        observer.subject = this;
    }

    public void unregisterObserver(MyObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(MyObserver::update);
    }


}

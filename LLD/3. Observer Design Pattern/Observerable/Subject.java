package Observerable;

import Observer.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removremoveObserver(Observer o);
    void notifyObservers();
}

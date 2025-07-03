package Observerable;

import java.util.ArrayList;
import java.util.List;

import Observer.Observer;

public class IPhoneObservable implements Subject {

    // List of Observers observing this Observable/Subject.
    List<Observer> observers;

    // Data
    int quantity;

    IPhoneObservable() {
        observers = new ArrayList<>();
        quantity = 0;
    }

    // Getters so that observer can easily get the required data 
    // rather than subject pushing it to observer
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);    
    }

    @Override
    public void removremoveObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.notifyObserver());
    }
    
}

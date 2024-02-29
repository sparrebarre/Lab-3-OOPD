package src;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }

    public void removeObserver(Observer o) { observers.remove(o); }

    protected void notifyObservers(String[] event) {
        for (Observer observer : observers) observer.notify(event);
    }
}

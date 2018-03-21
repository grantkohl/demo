package demo.designPattern.observer;

import java.util.Observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer observer = new ObserverImpl();

        subject.addObserver(observer);

        subject.setCount(5);

        subject.notifyObservers("calling");
    }
}

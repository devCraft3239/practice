package design.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 in observer desing pattern objects are bound such that changes in target/subject object are observed by subscribers/observer.
 Define a one-to-many dependency between objects so that
 when 'one object changes state, all its dependents/subscriber are notified and updated automatically'.
 * */
public class ObserverDesignPattern {
}

abstract class Subject{ // producer
    abstract void addObserver(Observer o);
    abstract void removeObserver(Observer o);
    abstract void notifyObservers();
    List<Observer> observers;
}

abstract class Observer{ // subscriber
    Subject subject;
    abstract void update();
}

class CricketDataUpdate extends Subject { // here Cricket data is subject which is subscribed by observer
    int run;
    int wicket;
    int over;

    CricketDataUpdate(List<Observer> observers){
        this.observers =  observers;
    }

    CricketDataUpdate(){
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update();
        }
    }

    public void updateDataChanges(int run, int over, int wicket){
        this.run =  run;
        this.over = over;
        this.wicket =  wicket;
        notifyObservers();
    }
}

class CurrentScoreDisplay extends Observer{
    public CurrentScoreDisplay(CricketDataUpdate subject){
        this.subject = subject;
        this.subject.addObserver(this); //two way mapping
    }

    @Override
    public void update() {
        displayCurrentScore();
    }

    public void displayCurrentScore(){
        System.out.println("\nCurrent Score Display:\n"
                + "Runs: " + ((CricketDataUpdate) subject).run +
                "\nWickets:" + ((CricketDataUpdate) subject).wicket +
                "\nOvers: " + ((CricketDataUpdate) subject).over);
    }
}

class ObserverRunner{
    public static void main(String[] args) {
        CricketDataUpdate cricketDataUpdate =  new CricketDataUpdate();
        CurrentScoreDisplay currentScoreDisplayObserver =  new CurrentScoreDisplay(cricketDataUpdate);
        cricketDataUpdate.updateDataChanges(10,2,1);
    }
}



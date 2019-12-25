package aspects;

import contest.ctrl.ViewResultsHandler;
import contest.model.Contest;

import java.util.ArrayList;
import java.util.List;

public aspect ContestObserverPattern {
    declare parents:Contest implements Subject;
    declare parents:ViewResultsHandler implements Observer;



    private List<Observer> Subject.observers = new ArrayList<Observer>();;

    public void Subject.addObserver(Observer obs) {
        System.out.println("Adding observer");
        observers.add(obs);
    }
    public void Subject.removeObserver(Observer obs) {
        System.out.println("Removing observer");
        observers.remove(obs);

    }
    public void Subject.notifyObservers(Object o) {
        for (Observer ob : observers)
            ob.update(o);
    }

    pointcut observed(Contest conc):execution(* contest.model.Contest.adauga*(..)) && this(conc);

    Contest cc;
    //adding an observer
    after(Contest conc, ViewResultsHandler handler): initialization(contest.ctrl.ViewResultsHandler.new(*))
            &&this(handler)&&args(conc){
        conc.addObserver(handler);
        cc = conc;
    }
    //observers notification
    after(Contest conc) returning: observed(conc){
        System.out.println("Inside ObserverAspect: notifyObservers");
        conc.notifyObservers(null);

    }


    //observer action
    public void ViewResultsHandler.update(Object o) {
        System.out.println("Inside ObserverAspect: ViewResultsHandler.update ");
        loadParticipants();

    }

    //removing an observer
    after(ViewResultsHandler handler):execution(* ViewResultsHandler.close())&& this(handler){
        cc.removeObserver(handler);
    }

}

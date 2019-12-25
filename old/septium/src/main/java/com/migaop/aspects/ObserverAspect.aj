package com.migaop.aspects;

import com.migaop.controller.PersonFxController;
import com.migaop.custom.MyObserver;
import com.migaop.custom.Subject;
import com.migaop.service.PersonService;

import java.util.ArrayList;
import java.util.List;

public aspect ObserverAspect {
    declare parents:PersonService implements Subject;
    declare parents:PersonFxController implements MyObserver;

    public PersonService personService;

    private List<MyObserver>Subject.observers = new ArrayList<>();

    public void Subject.addObserver(MyObserver obs) {
        System.out.println("Adding observer");
        observers.add(obs);
    }

    public void Subject.removeObserver(MyObserver obs) {
        System.out.println("Removing observer");
        observers.remove(obs);

    }
    public void Subject.notifyObservers(Object o) {
        for (MyObserver ob : observers) {
            ob.update(o);
            System.out.println("Observer " + ob + " notified");
        }
    }

    pointcut register(com.migaop.controller.PersonFxController fxController):
            execution(* com.migaop.controller.PersonFxController.setPersonService*(*))&&this(fxController);

    pointcut subjectChanger(com.migaop.controller.PersonFxController controller):@annotation(com.migaop.custom.SubjectChangerMethod)&&this(controller);


    after(com.migaop.controller.PersonFxController fxController)returning:subjectChanger(fxController){
        System.out.println("subject is being changed");
        personService.notifyObservers(null);
    }

    after(com.migaop.service.PersonService service, com.migaop.controller.PersonFxController controller)
            returning:register(controller)
            &&args(service){
        System.out.println(controller);
        service.addObserver(controller);
        personService = service;
    }

    public void PersonFxController.update(Object o) {
        System.out.println("inside observer aspect");
        populatePersonList();
    }


}

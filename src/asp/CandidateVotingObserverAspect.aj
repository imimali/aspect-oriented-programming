package asp;

import sample.MainController;
import sample.VoterController;

import java.util.ArrayList;
import java.util.List;

public aspect CandidateVotingObserverAspect {
    declare parents:MainController implements Subject;
    declare parents:VoterController implements Observer;

    public MainController mc;

    private List<Observer> Subject.observers = new ArrayList<Observer>();

    public void Subject.addObserver(Observer obs) {
        System.out.println("Adding observer");
        observers.add(obs);
    }
    public void Subject.removeObserver(Observer obs) {
        System.out.println("Removing observer");
        observers.remove(obs);

    }
    public void Subject.notifyObservers(Object o) {
        for (Observer ob : observers) {
            ob.update(o);
            System.out.println("Observer " + ob + " notified");
        }
    }
    public CandidateVotingObserverAspect(){
        System.out.println("being created");
    }


    pointcut observed(MainController mainController):execution(* sample.MainController.addCandidate*(..))
            &&this(mainController);

    pointcut sayHello(VoterController voterController):execution(* sample.VoterController.sayHelloToMain*(*))
            &&this(voterController);

    pointcut subjectChanger(VoterController voterController):@annotation(SubjectChangerMethod)&&this(voterController);

    after(VoterController voterController)returning:subjectChanger(voterController){
        System.out.println("changing subject" + mc);
        mc.notifyObservers(null);
        mc.populateCandidatesList();
    }


    after(MainController mainController, VoterController voterController)
            returning:sayHello(voterController)
            &&args(mainController){
        mainController.addObserver(voterController);
        System.out.println("do I really do"+voterController);
        mc = mainController;
    }


    after(MainController mainController)returning:observed(mainController){
        System.out.println("inside observer yehaa" + mainController);
        mainController.notifyObservers(null);
    }

    public void VoterController.update(Object o) {
        System.out.println("inside observer aspect");
        populateCandidatesList();
    }


}

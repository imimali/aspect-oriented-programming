package aspects;


import contest.model.Contest;
import contest.ctrl.ViewResultsHandler;
public privileged aspect ContestObserver extends AbstractObserver{
	declare parents: Contest implements Subject;
	declare parents: ViewResultsHandler implements Observer;

   
	protected pointcut observed(Subject o):execution(* contest.model.Contest.adauga*(..)) && target(o);
	
	//adding an observer
	after(Contest conc, ViewResultsHandler handler): initialization(contest.ctrl.ViewResultsHandler.new(*))
	&&this(handler)&&args(conc){
		conc.addObserver(handler);
	}
	
	//removing an observer	
	after(ViewResultsHandler handler):execution(* ViewResultsHandler.close())&& target(handler){
		Contest con=handler.concurs;
		con.removeObserver(handler);
		
	}

	//observer action
	public void ViewResultsHandler.update(Object o){
		System.out.println("call loadParticipants");
		loadParticipants();
	}

}

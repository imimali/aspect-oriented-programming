package aspects;
import java.util.*;
public abstract aspect AbstractObserver {
	protected interface Subject{
		void addObserver(Observer o);
		void removeObserver(Observer o);
		void notifyObservers(Object newData);
		
	}
	
	protected interface Observer{
		void update(Object newData);
	}

	private Set<Observer> Subject.observers=new HashSet<Observer>();
	public void Subject.addObserver(Observer o){
		System.out.println("Adding observer");
		observers.add(o);
	}
	public void Subject.removeObserver(Observer o){
		System.out.println("Removing observer");
		observers.remove(o);
	}
	public void Subject.notifyObservers(Object newData){
		for(Observer obs:observers){
			obs.update(newData);
		}
	}
	
	protected abstract pointcut observed(Subject o);
	
	after (Subject o) returning:observed(o){
		o.notifyObservers(null);
	}
}

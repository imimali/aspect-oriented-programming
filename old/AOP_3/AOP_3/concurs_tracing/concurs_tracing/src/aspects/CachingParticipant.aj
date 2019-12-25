package aspects;
import concurs.model.Participant;
import java.util.*;
public aspect CachingParticipant {
	private Map<String, Participant> cache;
	
	public CachingParticipant(){
		cache=new HashMap<String,Participant>();
	}
	
	pointcut cachedOperation(String id):execution(public Participant concurs..*Repository+.findById(*))&&args(id);
	
	 Participant around(String id) : cachedOperation(id){
		 Participant p=cache.get(id);
		 if (p==null){
			 p=proceed(id);
			 cache.put(id, p);
			 
		 }
		 return p;
		
	}
	
}

package aspects;

public aspect CacheMonitoring {
	pointcut cacheRetrieval(String key):call(* java.util.Map+.get(*))&&args(key)&& within(CachingParticipant);
	
	after(Object key) returning(Object value) : cacheRetrieval(key) {
		if (value!=null)
			System.out.println("Participant from cache " + value);
		else
			System.out.println("Participant from repository ");
	}

}

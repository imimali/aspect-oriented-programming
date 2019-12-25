package aspects;

import remoting.AOPRemoteException;

public aspect FailureHandlingAspect {
	private final int MAX_RETRIES = 3;
	Object around() : call(* remoting.RemoteService.*(..)) {
		int retry = 0;
		while(true){
			try{
				return proceed();
			} catch(AOPRemoteException ex){
				System.out.println("Encountered " + ex);
				if (++retry > MAX_RETRIES) {
					throw ex;
				}
				System.out.println("\tRetrying...");
			}
		}
	}
}

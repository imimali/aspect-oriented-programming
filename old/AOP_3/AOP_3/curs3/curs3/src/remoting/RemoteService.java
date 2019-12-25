package remoting;

public class RemoteService {
	
	public int getReply(){
		if(Math.random() > 0.25) {
			throw new AOPRemoteException("Simulated failure...");
		}
		System.out.println("Not failure. Replying");
		return 10;
	}
}

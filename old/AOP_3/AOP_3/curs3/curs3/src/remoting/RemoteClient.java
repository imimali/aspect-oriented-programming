package remoting;

public class RemoteClient {
	public static void main(String[] args) {
		try{
			RemoteService service = new RemoteService();
			int retVal = service.getReply();
			System.out.println("Reply is " + retVal);
		}catch(AOPRemoteException ex){
			System.out.println("Exception in RemoteClient:main: "+ex);
		}
	}
}

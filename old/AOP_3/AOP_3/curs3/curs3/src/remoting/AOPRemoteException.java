package remoting;

public class AOPRemoteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AOPRemoteException() {
		super();
		
	}

	public AOPRemoteException(String msg, Throwable ex) {
		super(msg, ex);
		
	}

	public AOPRemoteException(String msg) {
		super(msg);
		
	}

	public AOPRemoteException(Throwable ex) {
		super(ex);
		
	}
	

}

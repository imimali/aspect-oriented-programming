package aspects;
import java.util.logging.*;

public abstract aspect AbstractPerformanceMonitoringAspect {
	private Logger logger= Logger.getLogger("Contest");
	
	public abstract pointcut  monitoredOp();

	 Object around(): monitoredOp(){
		 long start = System.nanoTime();
		 try {
			 return proceed();
		 } finally {
			 long complete = System.nanoTime();
			 logger.log(Level.INFO,	"Operation " + thisJoinPointStaticPart.getSignature().toShortString()
					 + " took " + (complete-start) + " nanoseconds");
		 }
	}
}

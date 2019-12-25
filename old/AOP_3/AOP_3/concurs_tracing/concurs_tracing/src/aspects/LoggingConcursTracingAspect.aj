package aspects;
import java.util.logging.*;
public aspect LoggingConcursTracingAspect {
	pointcut tracing():call(public * concurs..*.*(..))&& !within(LoggingConcursTracingAspect);
	
	private Logger logger=Logger.getLogger("concurs");
	before(): tracing(){
		logger.info("Entering: "+thisJoinPointStaticPart.getSignature());
		//System.out.println("Entering method: "+thisJoinPointStaticPart.getSignature());
	}
	
	after():tracing(){
		//printIndentation();
		//System.out.println("Exiting method: "+thisJoinPointStaticPart.getSignature());
		logger.info("Exiting: "+thisJoinPointStaticPart.getSignature());
		//indentation--;
		
	}
	
	

}

//java StartApp -Djava.util.logging.config.file=logging.properties
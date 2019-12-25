package aspects;
import org.aspectj.lang.JoinPoint;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public aspect Log4JConcursTracingAspect {
	public Log4JConcursTracingAspect(){
		//PropertyConfigurator.configure("log4j.properties");
	}
	pointcut tracing():execution(public * concurs..*.*(..))&& !(within(Log4JConcursTracingAspect)||within(concurs.gui.*));
		
	
	private Logger logger=LogManager.getLogger("concurs");
	before(): tracing(){
		logger.traceEntry("{} Arguments value {}",thisJoinPointStaticPart.getSignature(),thisJoinPoint.getArgs());
				//createParams(thisJoinPoint));
    }
	
	after():tracing(){		
		logger.traceExit("{}",thisJoinPointStaticPart.getSignature());
			
	}
	
	/*String createParams(JoinPoint joinPoint){
		Object[] args=joinPoint.getArgs();
		if (args.length>0){
			StringBuffer result=new StringBuffer();
			result.append("Arguments value: ");
			for(Object o:args){
				result.append(" "+o);
			}
			return result.toString();
		}
		return "";
	}*/
	
	

}

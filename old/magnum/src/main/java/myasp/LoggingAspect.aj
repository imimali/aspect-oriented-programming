package myasp;

import java.util.logging.Logger;

public aspect LoggingAspect {
    private static final Logger log = Logger.getLogger(LoggingAspect.class.getName());
    pointcut logMessage():execution(public * persistence.Repository.*(..));
    before():logMessage(){
        log.info(thisJoinPoint.getSignature() + " method entered");
        //System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+" method entered");
    }
    after():logMessage(){
        log.info(thisJoinPoint.getSignature() + " method finished");
    }
}

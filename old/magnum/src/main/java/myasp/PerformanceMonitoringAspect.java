package myasp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

@Aspect
public class PerformanceMonitoringAspect {
    private Logger logger = Logger.getLogger("Voting");

    @Pointcut("@annotation(EnablePerformanceMonitoring)")
    public void monitoredOperation(){}

    @Around("monitoredOperation()")
    public Object logStartAndEnd(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        String className = pjp.getTarget().getClass().getCanonicalName();
        String methodName = pjp.getSignature().getName();
        //logger.info("started method : " + className+"."+methodName);

        Object obj;
        try {
            obj = pjp.proceed();
            //logger.info("finished method : " + className+"."+methodName);
            return obj;
        } catch (Throwable e) {
            throw e;
        }finally {
            logger.info("Method "+className+"."+methodName+" execution lasted:"+((System.currentTimeMillis() - startTime )/1000f)+" seconds");
        }
    }
}

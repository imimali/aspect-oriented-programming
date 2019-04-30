package myasp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;


@Aspect
public class LoggingConcursTracingAspect {

    public LoggingConcursTracingAspect() {
        System.out.println("Aspect constructor");
    }

    @Pointcut("execution(public * persistence..*.*(..))&& !within(myasp.LoggingConcursTracingAspect)")
    public void tracing() {
    }

    private Logger logger = Logger.getLogger("concurs");

    @Before("tracing()")
    public void trace(JoinPoint jp) {
        //logger.info("Entering: "+jp.getSignature());
        System.out.println("Aspect Entering method: " + jp.getSignature());
    }

    @After("tracing()")
    public void afterTrace(JoinPoint jp) {
        //printIndentation();
        System.out.println("Aspect Exiting method: " + jp.getSignature());
        //logger.info("Exiting: "+jp.getSignature());
        //indentation--;

    }



}
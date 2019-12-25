package myasp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import persistence.Candidate;

import java.util.List;

@Aspect
public class CachingAspect {
    private List<Candidate> cache;

    public CachingAspect() {

    }

    @Pointcut("@annotation(EnableCaching)")
    public void cachedOperation() {
    }

    @Around("cachedOperation()")
    List<Candidate> getFromCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            if (cache == null) {
                List<Candidate> candidates = (List<Candidate>) proceedingJoinPoint.proceed();
                cache = candidates;
                return candidates;
            } else {
                return cache;
            }
        } catch (Throwable t) {
            throw t;
        }
    }
}

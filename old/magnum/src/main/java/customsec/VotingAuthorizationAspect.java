
package customsec;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import security.AbstractAuthorizationAspect;

@Aspect
public class VotingAuthorizationAspect extends AbstractAuthorizationAspect {
    @Pointcut("@annotation(RequiresAuthorization)")
    public void beanAnnotatedWithRequiresAuthorization() {
    }


    @Pointcut("beanAnnotatedWithRequiresAuthorization()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {
    }

    //@Pointcut("execution(* concurs.model.ConcursService.adauga*(..))")
    @Pointcut("beanAnnotatedWithRequiresAuthorization()")
    public void authorizationRequired() {
    }

}
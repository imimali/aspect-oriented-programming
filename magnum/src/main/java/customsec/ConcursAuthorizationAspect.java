
package customsec;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import security.AbstractAuthorizationAspect;

@Aspect
public class ConcursAuthorizationAspect extends AbstractAuthorizationAspect {
    @Pointcut("@annotation(RequiresAuthorization)")
    public void beanAnnotatedWithRequiresAuthorization() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    //@Pointcut("publicMethod() && beanAnnotatedWithRequiresAuthorization()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {
    }

    //@Pointcut("execution(* concurs.model.ConcursService.adauga*(..))")
    @Pointcut("publicMethod() && beanAnnotatedWithRequiresAuthorization()")
    public void authorizationRequired() {
    }
}
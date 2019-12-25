
package concurs.aspects.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import ajia.security.AbstractAuthorizationAspect;

@Aspect
public class ConcursAuthorizationAspect extends AbstractAuthorizationAspect {
    @Pointcut("execution(* concurs.model.ConcursService.adauga*(..))")
    public void authorizationRequired() {
    }
}
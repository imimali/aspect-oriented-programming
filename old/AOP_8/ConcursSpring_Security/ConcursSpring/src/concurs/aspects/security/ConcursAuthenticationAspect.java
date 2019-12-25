

package concurs.aspects.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import ajia.security.AbstractAuthenticationAspect;

@Aspect
public class ConcursAuthenticationAspect extends
        AbstractAuthenticationAspect {
    @Pointcut("execution(* concurs.model.ConcursService.adauga*(..))")
    public void authenticationRequired() {
    }
}
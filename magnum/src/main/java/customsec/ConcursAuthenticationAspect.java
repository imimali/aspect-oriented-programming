

package customsec;

import org.aspectj.lang.annotation.*;
import sample.VoterController;
import security.AbstractAuthenticationAspect;

@Aspect
public class ConcursAuthenticationAspect extends
        AbstractAuthenticationAspect {

    // @Pointcut("@annotation(RequiresAuthentication)")
    @Pointcut("within(@customsec.RequiresAuthentication *)")
    public void beanAnnotatedWithRequiresAuthentication() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("execution(* persistence.Repository.*(..))")
    public void explicitPersistenceMethodPointcut(){}


    @Pointcut("@annotation(RequiresAuthentication)&&this(voterController)")
    public void somethingInTheContextOfOhMyFuckingGod(VoterController voterController){}

    //@Pointcut("execution(* persistence.Repository.update*(..))")
    //@Pointcut("publicMethod() && beanAnnotatedWithRequiresAuthentication()")
    //@Before("publicMethodInsideAClassMarkedWithAtMonitor()")
    @Pointcut("@annotation(RequiresAuthentication)")
    public void authenticationRequired() {
        // System.out.println("you should authenticate");
    }

    @Before("explicitPersistenceMethodPointcut()")
    public void doItMyself() {
        System.out.println("oh god this is the only way it works");
    }

}
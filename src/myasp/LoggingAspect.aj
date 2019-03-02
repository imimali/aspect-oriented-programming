package myasp;

public aspect LoggingAspect {
    pointcut logMessage():execution(public * MainController.*(..));
    before():logMessage(){
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+" method entered");
    }
}

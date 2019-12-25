package aspects;

public aspect SystemMonitoringAspect extends AbstractPerformanceMonitoringAspect {

	public pointcut monitoredOp(): execution(* contest..*.*(..)) && !within(aspects..*);

}

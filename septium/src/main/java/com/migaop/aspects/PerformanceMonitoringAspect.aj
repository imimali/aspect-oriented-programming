package com.migaop.aspects;

import java.util.logging.Logger;

public aspect PerformanceMonitoringAspect {
    private final static Logger logger = Logger.getLogger("PerformanceMonitoring");
    pointcut monitoredOperation():@annotation(com.migaop.custom.EnablePerformanceMonitoring);

    Object around():monitoredOperation() {
        long startTime = System.currentTimeMillis();

        try {
            return proceed();
        } finally {
            logger.info("method " + thisJoinPointStaticPart.getSignature() + " executed in " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }
}

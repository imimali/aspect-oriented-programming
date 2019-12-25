package com.migaop.aspects;

import java.util.logging.Logger;

public aspect LoggingAspect {
    private final static Logger logger = Logger.getLogger("PersonRegistry");

    pointcut logging():execution(public * com.migaop..*.*(..))&&!(within(com.migaop.aspects.*)&&within(com.migaop.aspects.LoggingAspect));

    before():logging(){
        logger.info("Method entered: " + thisJoinPointStaticPart.getSignature());
    }
    after():logging(){
        logger.info("Method exit: " + thisJoinPointStaticPart.getSignature());
    }
}

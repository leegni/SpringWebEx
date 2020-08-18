package com.care.WebPage2;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	public void logPrint(JoinPoint joinPoint) {
		logger.info("### Requested class : {} ; Method : {} ", joinPoint.getTarget().getClass().getName(),
				joinPoint.getSignature().getName());
		Object[] signatureArgs = joinPoint.getArgs();
		for (Object signatureArg : signatureArgs) {
			if (signatureArg != null)
				logger.info("###### Arguments: {} ", signatureArg.toString());
		}
	}

}

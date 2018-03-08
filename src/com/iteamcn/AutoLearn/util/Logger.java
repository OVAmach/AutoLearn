package com.iteamcn.AutoLearn.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger {
	private static Log log=LogFactory.getLog(Logger.class);
	public static Log getLogger(){
		return log;
	}
}

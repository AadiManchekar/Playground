package com.aadi.logeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogegApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogegApplication.class, args);
		JustLog justLog = new JustLog();
		justLog.logSomething();
		justLog.logError();
		justLog.logDebug();
		justLog.logWarn();
		justLog.logTrace();
		justLog.logFatal();
		justLog.spinUpAThread();
		
	}

}

package com.aadi.logeg;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;



@Component
@Log4j2
public class JustLog {
    public void logSomething() {
        log.info("This is a log message from JustLog class.");
    }
    public void logError() {
        log.error("This is an error message from JustLog class.");
    }
    public void logDebug() {
        log.debug("This is a debug message from JustLog class.");
    }
    public void logWarn() {
        log.warn("This is a warning message from JustLog class.");
    }
    public void logTrace() {
        log.trace("This is a trace message from JustLog class.");
    }
    public void logFatal() {
        log.fatal("This is a fatal message from JustLog class.");
    }
    public void spinUpAThread() {
        Thread thread = new Thread(() -> {
            log.info("This is a log message from a thread.");
        });
        thread.start();
    }
}

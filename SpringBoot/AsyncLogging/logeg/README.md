# LogEG: Asynchronous Logging with Log4j2 and LMAX Disruptor

## Overview

This project demonstrates the use of **Log4j2** for high-performance asynchronous logging in a Spring Boot application. The configuration leverages **LMAX Disruptor**, a high-throughput inter-thread messaging library, to optimize logging performance. By using asynchronous logging, the application minimizes the performance overhead of logging operations, ensuring that the main application threads are not blocked.

## Key Features

1. **Asynchronous Logging**:
   - Configured using the `<AsyncRoot>` logger in `log4j2.xml`.
   - Utilizes LMAX Disruptor for efficient asynchronous message handling.

2. **Custom Log Pattern**:
   - Defined in the `LOG_PATTERN` property for consistent and readable log formatting.
   - Example: 
     ```
     %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %highlight{%-5level} [%F:%L] %logger{36} - %msg%n
     ```

3. **Rolling File Appender**:
   - Logs are written to a file (`logs/app.log`) with automatic rolling based on size (`10MB`) and time.
   - Archived logs are compressed (`logs/app-<date>.log.gz`).

4. **Console Appender**:
   - Logs are also output to the console for real-time monitoring.


## Key Features

### 1. **Asynchronous Logging**
- Configured using the `<AsyncRoot>` logger in `log4j2.xml`.
- Utilizes **LMAX Disruptor**, a high-performance inter-thread messaging library, to handle log events asynchronously.
- Ensures that logging operations do not block the main application threads, improving application responsiveness.

### 2. **Custom Log Pattern**
- A custom log pattern is defined in the `LOG_PATTERN` property for consistent and readable log formatting.
```
2023-03-29 18:49:20.461 [main] INFO [LogegApplication.java:15] com.aadi.logeg.LogegApplication - This is a log message
```
- The pattern includes timestamps, thread names, log levels, file names, line numbers, and logger names.

### 3. **Rolling File Appender**
- Logs are written to a file (`logs/app.log`) with automatic rolling based on:
- **Size**: Rolls over when the file size exceeds `10MB`.
- **Time**: Rolls over daily.
- Archived logs are compressed into `.gz` files for efficient storage.

### 4. **Console Appender**
- Logs are also output to the console for real-time monitoring during development and debugging.



Performance Impact
Reduced Latency:

Asynchronous logging ensures that the main application threads are not blocked by I/O operations, leading to reduced latency and improved throughput.
High Throughput:

LMAX Disruptor is designed for high-performance inter-thread communication, enabling the application to handle a large volume of log messages efficiently.
Scalability:

The asynchronous logging setup can handle spikes in logging activity without degrading application performance.
Resource Optimization:

By offloading logging to a separate thread
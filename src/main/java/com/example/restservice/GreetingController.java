package com.example.restservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.MessageSupplier;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static com.example.restservice.configuration.LoggingMarker.SQL_MARKER;
import static com.example.restservice.configuration.LoggingMarker.TIMER_MAKER;

@RestController
public class GreetingController {
	private final Logger logger = LogManager.getLogger(GreetingController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		var result = new Greeting(counter.incrementAndGet(), String.format(template, name));
		logger.debug(result.getContent() + ":" + result.getId());
		logger.info(result.getContent() + ":" + result.getId());
		logger.info(SQL_MARKER, "SQL: " + result.getId());
		logger.info(TIMER_MAKER, "SQL TIMER: " + result.getId());
		logger.info("password is: ABCD");
		logger.debug("password is: ABCD");
		logger.warn(result.getContent() + ":" + result.getId());
		try {
			System.out.println(1/0);
		} catch (Exception ex) {
			logger.error(getMessageSupplier("password is: {}", "ABC", ex, "TESTSER"));
			logger.error(result.getContent() + ":" + result.getId(), ex);
		}
		return result;
	}

	private static MessageSupplier getMessageSupplier(String message, Object arguments, Throwable ex, String severity) {
		MDC.put("severity", severity);
		return () -> new ParameterizedMessage(message, arguments, ex);
	}
}

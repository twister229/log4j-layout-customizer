package com.example.restservice.configuration;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;

@Plugin(name = "CustomRewritePolicy", category = "Core",
        elementType = "rewritePolicy", printObject = true)
public class CustomRewritePolicy implements RewritePolicy {
    private CustomRewritePolicy() {
    }

    @PluginFactory
    public static CustomRewritePolicy createPolicy() {
        return new CustomRewritePolicy();
    }

    @Override
    public LogEvent rewrite(LogEvent event) {
        String message = event.getMessage().getFormattedMessage();

        // write your code to manipulate your message here
        message = message.replaceAll("password", "*******");

        return Log4jLogEvent.newBuilder()
                .setLoggerName(event.getLoggerName())
                .setMarker(event.getMarker())
                .setLoggerFqcn(event.getLoggerFqcn())
                .setLevel(event.getLevel())
                .setMessage(new SimpleMessage(message))
                .setThrown(event.getThrown())
                .setContextMap(event.getContextMap())
                .setContextStack(event.getContextStack())
                .setThreadName(event.getThreadName())
                .setSource(event.getSource())
                .setTimeMillis(event.getTimeMillis())
                .build();
    }
}

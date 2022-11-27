package com.example.restservice.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

@Plugin(name = "EventTypeConvert", category = PatternConverter.CATEGORY)
@ConverterKeys({"event"})
public class EventTypeConverter extends LogEventPatternConverter {
    protected EventTypeConverter(String name, String style) {
        super(name, style);
    }

    public static EventTypeConverter newInstance(String[] options) {
        return new EventTypeConverter("event", "");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        if (event.getMarker() != null) {
            toAppendTo.append(event.getMarker().getName());
        } else if (event.getLevel() == Level.WARN || event.getLevel() == Level.ERROR) {
            toAppendTo.append("EXCEPTION");
        } else {
            toAppendTo.append("OTHERS");
        }
    }
}

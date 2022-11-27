package com.example.restservice.configuration;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LoggingMarker {

    public static final Marker SQL_MARKER = MarkerManager.getMarker("SQL");
    public static final Marker TIMER_MAKER = MarkerManager.getMarker("TIMER");

    public static final Marker ERROR = MarkerManager.getMarker("ERROR");

    public static final Marker ERROR_HIGHEST = MarkerManager.getMarker("ERROR").addParents(ERROR);

}

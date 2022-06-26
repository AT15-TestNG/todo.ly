package utils;

import framework.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class LoggerManager {
    private Logger logger;
    private static LoggerManager instance;

    private LoggerManager() {
        initialize();
    }

    public static LoggerManager getInstance() {
        return (instance == null) ? instance = new LoggerManager() : instance;
    }

    private void initialize() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("log4j.properties");
        context.setConfigLocation(file.toURI());
        logger = LogManager.getLogger(LoggerManager.class);
    }

    public void logMessage(String type, String message) {
        switch (type) {
            case "debug" -> getInstance().logger.debug(message);
            case "error" -> getInstance().logger.error(message);
            case "info" -> getInstance().logger.info(message);
            case "warn" -> getInstance().logger.warn(message);
        }
    }

    public void debug(String message) {
        logMessage("debug", message);
    }

    public void error(String message) {
        logMessage("error", message);
    }

    public void info(String message) {
        logMessage("info", message);
    }

    public void warn(String message) {
        logMessage("warn", message);
    }
}
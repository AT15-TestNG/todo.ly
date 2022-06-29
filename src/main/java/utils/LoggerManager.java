package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

/**
 * LoggerManager class manage all the captured logs during the
 * test cases execution. It needs log4j2.properties file to initialize.
 * @author TestNG group: <a href="mailto:adrian.oviedo@fundacion-jala.org">Adrian Oviedo</a>,
 * <a href="mailto:agustin.mediotti@fundacion-jala.org">Agustin Mediotti</a>,
 * <a href="mailto:jimy.tastaca@fundacion-jala.org">Jimy Tastaca</a>,
 * <a href="mailto:saul.fuentes@fundacion-jala.org">Saul Fuentes</a>,
 * <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 */
public class LoggerManager {
    private Logger logger;
    private static LoggerManager instance;

    /**
     * LoggerManager constructor.
     */
    private LoggerManager() {
        initialize();
    }

    /**
     * Get the instance of the LoggerManager class.
     * @return the instance of the LoggerManager class.
     */
    public static LoggerManager getInstance() {
        if (instance == null) {
            instance = new LoggerManager();
        }
        return instance;
    }

    /**
     * Initialize the LoggerManager class.
     */
    private void initialize() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("log4j2.properties");
        context.setConfigLocation(file.toURI());
        logger = LogManager.getLogger(LoggerManager.class);
    }

    /**
     * Logs a String message according to its type.
     * @param type the type of the message.
     *             The possible values are:
     *             debug, error, info, warn.
     * @param message the message to be logged.
     * @return the message logged.
     */
    public void logMessage(String type, String message) {
        switch (type) {
            case "debug" : getInstance().logger.debug(message);
            case "error" : getInstance().logger.error(message);
            case "info" : getInstance().logger.info(message);
            case "warn" : getInstance().logger.warn(message);
        }
    }

    /**
     * Logs a debug message.
     * @param message the message to be logged.
     */
    public void debug(String message) {
        logMessage("debug", message);
    }

    /**
     * Logs an error message.
     * @param message the message to be logged.
     */
    public void error(String message) {
        logMessage("error", message);
    }

    /**
     * Logs an info message.
     * @param message the message to be logged.
     */
    public void info(String message) {
        logMessage("info", message);
    }

    /**
     * Logs a warn message.
     * @param message the message to be logged.
     */
    public void warn(String message) {
        logMessage("warn", message);
    }
}
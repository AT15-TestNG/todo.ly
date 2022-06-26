package framework;

import utils.LoggerManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    private Properties properties;
    private static final LoggerManager log = LoggerManager.getInstance();
    private static Environment instance;

    private Environment() {
        initialize();
    }

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    private void initialize() {
        log.info("Reading properties");
        properties = new Properties();
        try {
            File file = new File("gradle.properties");
            FileReader fileReader = new FileReader(file);
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            log.error("unable to read properties file");
        }
    }

    private String getEnvironmentSetting(String setting) {
        return (String) getInstance().properties.get(setting);
    }

    public String getBaseURL() {
        return getEnvironmentSetting("baseURL");
    }

    public String getUserName() {
        return getEnvironmentSetting("userName");
    }

    public String getPassword() {
        return getEnvironmentSetting("password");
    }

    public String getInvalidUserName() {
        return getEnvironmentSetting("invalidUserName");
    }

    public String getInvalidPassword() {
        return getEnvironmentSetting("invalidPassword");
    }

    public String getBasePath() {
        return getEnvironmentSetting("basePath");
    }

    public String getProjectsEndpoint() {
        return getEnvironmentSetting("projectsEndpoint");
    }

    public String getFiltersEndpoint() {
        return getEnvironmentSetting("filtersEndPoint");
    }

    public String getFiltersByIdEndPoint() {
        return getEnvironmentSetting("filtersByIdEndPoint");
    }

    public String getItemsOfFilterEndpoint() {
        return getEnvironmentSetting("itemsOfAFilterEndpoint");
    }

    public String getDoneItemsOfFilterEndpoint() {
        return getEnvironmentSetting("doneItemsOfAFilterEndpoint");
    }

    public String getUserEndpoint() {
        return getEnvironmentSetting("userEndpoint");
    }

    public String getUserByIdEndPoint() { return getEnvironmentSetting("userByIdEndPoint"); }
}

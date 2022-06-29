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

    /**
    * The method is used to get the instance of the class.
    * If the instance is null, it will create a new one.
    * @return the instance of the class.
    */

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    /**
    * This method is used to initialize the properties file.
    * @return the properties file.
    * @throws IOException if the file is not found.
    */

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

    /**
    * This method is used to get the value of a property.
    * @param setting the name of the property.
    * @return the value of the property.
    */

    private String getEnvironmentSetting(String setting) {
        return (String) getInstance().properties.get(setting);
    }

    public String getBaseURL() {
        return getEnvironmentSetting("baseURL");
    }

    public String getUserName() {
        return getEnvironmentSetting("userName");
    }
    public String getGetAllProjectsUser() {
        return getEnvironmentSetting("getAllProjectsUser");
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

    public String getGetAllProjectsPassword() {
        return getEnvironmentSetting("getAllProjectsPassword");
    }

    public String getBasePath() {
        return getEnvironmentSetting("basePath");
    }

    public String getProjectsEndpoint() {
        return getEnvironmentSetting("projectsEndpoint");
    }

    public String getItemsEndpoint() {
        return getEnvironmentSetting("itemsEndpoint");
    }
    public String getItemsByIdEndpoint() {
        return getEnvironmentSetting("itemByIdEndpoint");
    }
    public String getRootItemByIdChildEndpoint() {
        return getEnvironmentSetting("rootItemByIdChildEndpoint");
    }
    public String getDoneRootItemByIdChildEndpoint() {
        return getEnvironmentSetting("doneRootItemByIdChildEndpoint");
    }
    public String getItemsProjectsEndpoint() {
        return getEnvironmentSetting("itemsProjectByIdEndpoint");
    }
    public String getCreateItemEndpoint() {
        return getEnvironmentSetting("createItemEndpoint");
    }

    public String getProjectByIdEndpoint() {
        return getEnvironmentSetting("projectByIdEndpoint");
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

    public String getDoneItemsOfAProjectEndpoint() {
        return getEnvironmentSetting("doneItemsOfAProjectEndpoint");
    }

    public String getDoneItemsOfFilterEndpoint() {
        return getEnvironmentSetting("doneItemsOfAFilterEndpoint");
    }

    public String getUserEndpoint() {
        return getEnvironmentSetting("userEndpoint");
    }

    public String getUserByIdEndPoint() {
        return getEnvironmentSetting("userByIdEndPoint");
    }

    public String getIconsByIdEndPoint() {
        return getEnvironmentSetting("iconsByIdEndPoint");
    }

    public String getIsAuthenticatedEndPoint() {
        return getEnvironmentSetting("isAuthenticatedEndPoint");
    }

    public String getAuthenticationTokenEndPoint() {
        return getEnvironmentSetting("authenticationTokenEndPoint");
    }
}


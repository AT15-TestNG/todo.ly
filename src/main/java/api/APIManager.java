package api;

import framework.Environment;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import utils.LoggerManager;

public class APIManager {
    private static final LoggerManager log = LoggerManager.getInstance();
    private  static APIManager instance;

    private APIManager() {
        initialize();
    }

    public static APIManager getInstance() {
        return (instance == null) ? instance = new APIManager() : instance;
    }

    private static void initialize() {
        log.info("Initializing API Manager");
        RestAssured.baseURI = Environment.getInstance().getBaseURL();
        RestAssured.basePath = Environment.getInstance().getBasePath();
    }

    public void setCredentials(String userName, String password) {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(userName);
        authScheme.setPassword(password);
        RestAssured.authentication = authScheme;
    }

    public Response get(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

}

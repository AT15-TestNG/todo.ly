package api;

import framework.Environment;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import utils.LoggerManager;

public class APIManager {
    private static final LoggerManager log = LoggerManager.getInstance();
    private  static APIManager instance;

    public APIManager() {
        initialize();
    }

    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }
        return instance;
    }

    private void initialize() {
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
        return RestAssured
//                .given()
//                    .auth()
//                    .preemptive()
//                    .basic(Environment.getInstance().getUserName(), Environment.getInstance().getPassword())
                .when()
                    .get(endpoint)
                .then()
                    .extract()
                    .response();
    }

}

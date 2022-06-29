package api;

import framework.Environment;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.LoggerManager;

/**
 * The APIManager class implements all the methods for creating API requests.
 * @author TestNG group: <a href="mailto:adrian.oviedo@fundacion-jala.org">Adrian Oviedo</a>,
 * <a href="mailto:agustin.mediotti@fundacion-jala.org">Agustin Mediotti</a>,
 * <a href="mailto:jimy.tastaca@fundacion-jala.org">Jimy Tastaca</a>,
 * <a href="mailto:saul.fuentes@fundacion-jala.org">Saul Fuentes</a>,
 * <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 * @version 1.0
 */
public class APIManager {
    private static final LoggerManager log = LoggerManager.getInstance();
    private  static APIManager instance;

    private APIManager() {
        initialize();
    }

    /**
     * The method is used to get the instance of the class APIManager.
     * If the instance is null, it will create a new one.
     * @return the instance of the APIManager class.
    */
    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }
        return instance;
    }

    /**
     * This method is used to initialize the API using RestAssured class.
     * @return the RestAssured with baseURi and basePath set.
    */
    private void initialize() {
        log.info("Initializing API Manager");
        RestAssured.baseURI = Environment.getInstance().getBaseURL();
        RestAssured.basePath = Environment.getInstance().getBasePath();
    }

    /**
     * This method is used to set the credentials for the application.
     * It uses PreemptiveBasicAuthScheme for set the basic authentication.
     * @param userName the logging username.
     * @param password the logging password.
     * @return RestAssured with the authentication established with the given credentials.
    */
    public void setCredentials(String userName, String password) {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(userName);
        authScheme.setPassword(password);
        RestAssured.authentication = authScheme;
    }

    /**
     * This method is used to set the Token value for the authentication.
     * @param token the value of the Token.
     * @return the header of the request containing the token.
    */
    public RequestSpecification setTokenAuthentication(String token) {
        return RestAssured.given().header("Token", token);
    }

    /**
     * This method is used to generate a Token for the user that is currently logged.
     * @return the token for the logged user.
    */
    public Response getTokenAuthentication() {
        return getInstance().get(Environment.getInstance().getAuthenticationTokenEndPoint());
    }

    /**
     * This method is used to do a GET request to a specific endpoint.
     * @param endpoint the endpoint for the request.
     * @return the response of the GET request.
    */
    public Response get(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

    /**
     * This method is used to do a POST request to a specific endpoint.
     * @param endpoint the endpoint for the request.
     * @param contentType the content type for the request.
     * @param object the body of the request.
     * @return the response of the POST request.
     */
    public Response post(String endpoint, ContentType contentType, Object object) {
        return RestAssured.given().contentType(contentType).body(object).post(endpoint);
    }

    /**
     * This method is used to do a DELETE request to a specific endpoint.
     * @param endpoint the endpoint for the request.
     * @return the response of the DELETE request.
     */
    public Response delete(String endpoint) {
        return  RestAssured.given().delete(endpoint);
    }

    /**
     * This method is used to do a PUT request to a specific endpoint.
     * @param endpoint the endpoint for the request.
     * @param contentType the content type for the request.
     * @param object the body of the request.
     * @return the response of the PUT request.
     */
    public Response put(String endpoint, ContentType contentType, Object object) {
        return RestAssured.given().contentType(contentType).body(object).put(endpoint);
    }

    /**
     * This method is used to do a GET request with body.
     * @param endpoint the endpoint for the request.
     * @param contentType the content type for the request.
     * @param object the body of the request.
     * @return the response of the GET request.
     */
    public Response getWithBody(String endpoint, ContentType contentType, Object object) {
        return RestAssured.given().contentType(contentType).body(object).get(endpoint);
    }
}


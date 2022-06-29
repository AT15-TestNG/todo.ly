package com.jalasoft.todoly.authentication;

import api.APIManager;
import framework.Environment;
import groovyjarjarantlr4.runtime.Token;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * This class implements the tests for the authentication endpoint.
 * @author TestNG group: @author TestNG group: <a href="mailto:saul.fuentes@fundacion-jala.org">Saul Fuentes</a>
 * @version 1.0
 */
public class AuthenticationTests {
    @Test
    public void basicAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET isAuthenticated request to the \"/authentication/isauthenticated.json\" is executed", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        Response response = apiManager.get(environment.getIsAuthenticatedEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("true"), "Correct response body is not returned");
    }

    @Test
    public void basicAuthenticationWithNonExistentUsername() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET isAuthenticated request to the \"/authentication/isauthenticated.json\" is executed with non-existent username", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials("non-existent-username@mail.com", environment.getPassword());

        Response response = apiManager.get(environment.getIsAuthenticatedEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("false"), "Correct response body is not returned");
    }

    @Test
    public void getTokenWithBasicAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET token request to the \"/authentication/token.json\" is executed", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        Response response = apiManager.get(environment.getAuthenticationTokenEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("TokenString"), "Correct response body is not returned");
        Assert.assertTrue(response.body().asString().contains("UserEmail"), "Correct response body is not returned");
        Assert.assertTrue(response.body().asString().contains("ExpirationTime"), "Correct response body is not returned");
    }

    @Test
    public void getTokenWithNonExistentUserName() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET token request to the \"/authentication/token.json\" is executed with non-existent username", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials("non-existen-username@mail.com", environment.getPassword());

        Response response = apiManager.get(environment.getAuthenticationTokenEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorMessage").toString().contains("Account doesn't exist"), "Correct response body is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorCode").toString().contains("105"), "Correct response body is not returned");
    }

    @Test
    public void getTokenWithUserNameAndInvalidPassword() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET token request to the \"/authentication/token.json\" is executed with valid username and invalid password", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), "invalid-password");

        Response response = apiManager.get(environment.getAuthenticationTokenEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorMessage").toString().contains("Not Authenticated"), "Correct response body is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorCode").toString().contains("102"), "Correct response body is not returned");
    }

    @Test
    public void getTokenWithoutAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET token request to the \"/authentication/token.json\" is executed without authentication", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();

        Response response = apiManager.get(environment.getAuthenticationTokenEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("Not Authenticated"), "Correct response body is not returned");
        Assert.assertTrue(response.body().asString().contains("102"), "Correct response body is not returned");
    }

    @Test
    public void isAuthenticatedWithValidToken() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET isAuthenticated request to the \"/authentication/isauthenticated.json\" is executed with valid token", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
        String token = apiManager.getTokenAuthentication().jsonPath().get("TokenString").toString();
        apiManager.setCredentials("", "");
        RequestSpecification requestSpecification = apiManager.setTokenAuthentication(token);

        Response response = requestSpecification.get(environment.getIsAuthenticatedEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("true"), "Correct response body is not returned");
    }

    @Test
    public void deleteTokenWithBasicAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a DELETE token request to the \"/authentication/token.json\" is executed", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
        String token = apiManager.getTokenAuthentication().jsonPath().get("TokenString").toString();
        RequestSpecification requestSpecification = apiManager.setTokenAuthentication(token);

        Response response = requestSpecification.delete(environment.getAuthenticationTokenEndPoint());
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().get("TokenString").toString().contains(token), "Correct response body is not returned");
        Assert.assertTrue(response.jsonPath().get("UserEmail").toString().contains(environment.getUserName()), "Correct response body is not returned");
        Assert.assertTrue(response.body().asString().contains("ExpirationTime"), "Correct response body is not returned");
    }

    @Test // Bug
    public void deleteTokenWithoutAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a DELETE token request to the \"/authentication/token.json\" is executed without authentication", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
        String token = apiManager.getTokenAuthentication().jsonPath().get("TokenString").toString();
        apiManager.setCredentials("", "");
        RequestSpecification requestSpecification = apiManager.setTokenAuthentication(token);

        Response response = requestSpecification.delete(environment.getAuthenticationTokenEndPoint());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        System.out.println(response.asString());
        Assert.assertTrue(response.jsonPath().get("ErrorMessage").toString().contains("Not Authenticated"), "Correct response body is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorCode").toString().contains("102"), "Correct response body is not returned");
    }

    @Test
    public void deleteInvalidTokenWithBasicAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a DELETE token request to the \"/authentication/token.json\" is executed with invalid token", true);
        APIManager apiManager = APIManager.getInstance();
        Environment environment = Environment.getInstance();
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
        String token = apiManager.getTokenAuthentication().jsonPath().get("TokenString").toString();
        RequestSpecification requestSpecification = apiManager.setTokenAuthentication(token + "a");

        Response response = requestSpecification.delete(environment.getAuthenticationTokenEndPoint());
        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorMessage").toString().contains("Invalid Token"), "Correct response body is not returned");
        Assert.assertTrue(response.jsonPath().get("ErrorCode").toString().contains("103"), "Correct response body is not returned");
    }
}

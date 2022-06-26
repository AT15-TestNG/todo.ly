package com.jalasoft.todoly.user;

import api.APIManager;
import entities.NewUser;
import entities.User;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UserErrorCodesTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @Test
    public void userNotAuthenticated() {
        Reporter.log("Verify that Error 102 Not Authenticated is returned in the response body when a GET request with invalid credentials to the \"/user.json\" endpoint is executed", true);
        api.setCredentials(environment.getUserName(), "badPassword");
        Response response = api.get(environment.getUserEndpoint());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Not Authenticated\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":102"), "Error Code was not returned");
    }

    @Test
    public void userAccountAlreadyExists() {
        Reporter.log("Verify that Error 201 Account Already Exists is returned in the response body when a POST request with existing credentials to the \"/user.json\" endpoint is executed", true);
        NewUser newUser = new NewUser("juanautomation@email.com", "password", "Juan Automation");
        Response response = api.post(environment.getUserEndpoint(), ContentType.JSON, newUser);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Account with this email address already exists\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":201"), "Error Code was not returned");
    }

    @Test
    public void userInvalidFullName() {
        Reporter.log("Verify that Error 306 Invalid FullName is returned in the response body when a POST request with a null FullName to the \"/user.json\" endpoint is executed", true);
        NewUser newUser = new NewUser("juannullname@email.com", "password", null);
        Response response = api.post(environment.getUserEndpoint(), ContentType.JSON, newUser);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Invalid FullName\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":306"), "Error Code was not returned");
    }
}

package com.jalasoft.todoly.user;

import api.APIManager;
import entities.NewUser;
import entities.User;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserBugs {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeMethod
    public void setup() {
        api.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void userInvalidRequest() {
        Reporter.log("Verify that Error 101 Invalid Request is returned in the response body when a GET request to the invalid \"user/1/items.json\" endpoint is executed", true);
        Response response = api.get("user/1/items.json");

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Invalid Request\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":101"), "Error Code was not returned");
    }

    @Test
    public void userGetWithBody() {
        Reporter.log("Verify that an Error is returned in the response body when a GET request with a Body to \"/user.json\" endpoint is executed", true);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Email", "juandelete@email.com");
        jsonAsMap.put("Password", "password");
        jsonAsMap.put("FullName", "Juan to be Deleted");
        Response response = api.getWithBody(environment.getUserEndpoint(), ContentType.JSON, jsonAsMap);
        User responseUser = response.as(User.class);

        System.out.println(responseUser.getEmail() + " " + responseUser.getFullName());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.body().asString().contains("ErrorCode"), "Error Code was returned");
        Assert.assertTrue(response.body().asString().contains("ErrorMessage"), "Error Message was returned");
    }

    @Test
    public void userContentLowerCaseBug() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a POST request with lower-case parameters to \"/user.json\" endpoint is executed", true);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "juandelete@email.com");
        jsonAsMap.put("Password", "password");
        jsonAsMap.put("FullName", "Juan to be Deleted");
        Response response = api.post(environment.getUserEndpoint(), ContentType.JSON, jsonAsMap);
        User responseUser = response.as(User.class);

        System.out.println(jsonAsMap.get("email"));
        System.out.println(jsonAsMap.get("FullName"));
        System.out.println(responseUser.getEmail());
        System.out.println(responseUser.getFullName());
        System.out.println(response.jsonPath().getString("ErrorMessage"));

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseUser.getEmail(), jsonAsMap.get("email"), "Incorrect Email value was set");
        Assert.assertEquals(responseUser.getFullName(), jsonAsMap.get("FullName"), "Incorrect FullName value was set");
    }

    @Test
    public void userPostWithIncorrectPayload() {
        Reporter.log("Verify that an Error is returned in the response body when a POST request with an incorrect Body format (payload) to \"/user.xml\" endpoint is executed", true);
        NewUser newUser = new NewUser("juandelete@email.com", "password", "Juan to be Deleted");
        Response response = api.post("/user.xml", ContentType.JSON, newUser);

        System.out.println(response.getStatusCode());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.body().asString().contains("ErrorCode"), "Error Code was returned");
        Assert.assertTrue(response.body().asString().contains("ErrorMessage"), "Error Message was returned");
    }
}

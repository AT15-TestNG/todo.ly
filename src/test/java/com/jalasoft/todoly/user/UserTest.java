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

public class UserTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();
    @BeforeMethod
    public void setup() {
        api.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void getUser() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET request to the \"/user.json\" endpoint is executed", true);
        Response response = api.get(environment.getUserEndpoint());
        User responseUser = response.as(User.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is not returned");
        Assert.assertEquals(responseUser.getEmail(), environment.getUserName(), "Incorrect Email was returned");
    }

    @Test
    public void updateUser() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a PUT request to the \"/user/0.json\" endpoint is executed", true);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("AddItemMoreExpanded", true);
        jsonAsMap.put("FirstDayOfWeek", 1);
        jsonAsMap.put("TimeZoneId", "SA Western Standard Time");
        Response response = api.put(environment.getUserByIdEndPoint(), ContentType.JSON, jsonAsMap);
        User responseUser = response.as(User.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseUser.getAddItemMoreExpanded(), jsonAsMap.get("AddItemMoreExpanded"), "Incorrect AddItemMoreExpanded was set");
        Assert.assertEquals(responseUser.getFirstDayOfWeek(), jsonAsMap.get("FirstDayOfWeek"), "Incorrect FirstDayOfWeek was set");
        Assert.assertEquals(responseUser.getTimeZoneId(), jsonAsMap.get("TimeZoneId"), "Incorrect TimeZoneId was set");
    }

    @Test
    public void createUser() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a POST request to the \"/user.json\" endpoint is executed", true);
        NewUser newUser = new NewUser("juandelete@email.com", "password", "Juan to be Deleted");
        Response response = api.post(environment.getUserEndpoint(), ContentType.JSON, newUser);
        User responseUser = response.as(User.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is not returned");
        Assert.assertEquals(responseUser.getEmail(), newUser.getEmail(), "Incorrect Email was set");
        Assert.assertEquals(responseUser.getFullName(), newUser.getFullName(), "Incorrect FullName was set");

        api.setCredentials("juandelete@email.com", "password");
        api.delete(environment.getUserByIdEndPoint());
    }

    @Test
    public void deleteUser() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a DELETE request to the \"/user/0.json\" endpoint is executed", true);
        NewUser newUser = new NewUser("juandelete@email.com", "password", "Juan to be Deleted");
        api.post(environment.getUserEndpoint(), ContentType.JSON, newUser);
        api.setCredentials("juandelete@email.com", "password");

        Response response = api.delete(environment.getUserByIdEndPoint());
        User responseUser = response.as(User.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is not returned");
        Assert.assertEquals(responseUser.getEmail(), newUser.getEmail(), "Incorrect Email was set");
        Assert.assertEquals(responseUser.getFullName(), newUser.getFullName(), "Incorrect FullName was set");
    }
}

package com.jalasoft.todoly.user;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();
    @BeforeClass
    public void setup() {
        api.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void getUser() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET request to the \"/user.json\" endpoint is executed", true);
        Response response = api.get(environment.getUserEndpoint());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is not returned");
    }
}

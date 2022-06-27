package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ItemsOfAFilterTests {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeClass
    public void setup()  {
        api.setCredentials(env.getUserName(), env.getPassword());
    }

    @Test (priority = 2)
    public void getAllItemsOfFilter() {
        Reporter.log("Verify that a 200 OK status code and message is received");
        String itemsOfAFilterEndpoint = String.format(env.getItemsOfFilterEndpoint(), -1);
        Response res = api.get(itemsOfAFilterEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Incorrect status code returned");
        Assert.assertTrue(res.statusLine().contains("200 OK"), "Incorrect status code and message");
        Assert.assertFalse(res.getBody().asString().contains("ErrorMessage"), "Correct response body is returned");
        Assert.assertFalse(res.getBody().asString().contains("ErrorCode"), "Correct response body is returned");
    }
}

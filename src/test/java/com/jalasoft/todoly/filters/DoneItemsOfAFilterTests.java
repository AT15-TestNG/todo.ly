package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DoneItemsOfAFilterTests {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @AfterClass
    public void setup() {
        api.setCredentials(env.getUserName(), env.getPassword());
    }

    @Test (priority = 2)
    public void getDoneItemsOfAFilter() {
        Reporter.log("Verify that a 200 OK status code and message is received");
        String doneItemsOfAFilterEndpoint = String.format(env.getDoneItemsOfFilterEndpoint(), -1);
        Response res = api.get(doneItemsOfAFilterEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Incorrect status code returned");
        Assert.assertTrue(res.statusLine().contains("200 OK"), "Incorrect status code and message returned");
    }
}
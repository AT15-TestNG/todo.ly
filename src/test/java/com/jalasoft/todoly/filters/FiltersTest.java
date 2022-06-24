package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FiltersTest {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        api.setCredentials(env.getUserName(), env.getPassword());
    }

    @Test (priority=2)
    public void getAllFilters() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET request to the \"/filters.json\" endpoint is executed", true);
        Response res = api.get(env.getFiltersEndpoint());
        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code");
    }

}

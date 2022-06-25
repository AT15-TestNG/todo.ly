package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FiltersByIdTests {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        api.setCredentials(env.getUserName(), env.getPassword());
    }

    @Test(priority = 2)
    public void getFiltersById() {
        String filterByIdEndpoint = String.format(env.getFiltersByIdEndPoint(), -1);
        Response res = api.get(filterByIdEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(res.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(res.jsonPath().getString("ErrorCode"), "Error Code was returned");
    }
}

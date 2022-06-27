package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FiltersInvalidIdTests {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        api.setCredentials(env.getUserName(), env.getPassword());
    }

    @Test(priority = 2)
    public void getFiltersByIdWithInvalidId() {
        String filterByIdEndpoint = String.format(env.getFiltersByIdEndPoint(), 5);
        Response res = api.get(filterByIdEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":301"), "Error code was not returned");
    }

    @Test(priority = 2)
    public void getDoneItemsOfAFilterWithInvalidId() {
        String doneItemsOfAFilterEndpoint = String.format(env.getDoneItemsOfFilterEndpoint(), 5);
        Response res = api.get(doneItemsOfAFilterEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":301"), "Error code was not returned");
    }
}

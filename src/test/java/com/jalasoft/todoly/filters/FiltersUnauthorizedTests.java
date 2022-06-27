package com.jalasoft.todoly.filters;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FiltersUnauthorizedTests {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        api.setCredentials(env.getInvalidUserName(), env.getInvalidPassword());
    }

    @Test(priority = 2)
    public void getAllFilters() {
        Response res = api.get(env.getFiltersEndpoint());

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Account doesn't exist"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":105"), "Error code was not returned");
    }

    @Test(priority = 2)
    public void getFiltersById() {
        String filterByIdEndpoint = String.format(env.getFiltersByIdEndPoint(), -1);
        Response res = api.get(filterByIdEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Account doesn't exist"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":105"), "Error code was not returned");
    }

    @Test(priority = 2)
    public void getItemsOfAFilter() {
        String itemsOfAFilterEndpoint = String.format(env.getItemsOfFilterEndpoint(), -1);
        Response res = api.get(itemsOfAFilterEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Account doesn't exist"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":105"), "Error code was not returned");
    }

    @Test(priority = 2)
    public void getDoneItemsOfAFilter() {
        String doneItemsOfAFilterEndpoint = String.format(env.getItemsOfFilterEndpoint(), -1);
        Response res = api.get(doneItemsOfAFilterEndpoint);

        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(res.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(res.jsonPath().getString("ErrorMessage").contains("Account doesn't exist"), "Correct ErrorMessage is not returned");
        Assert.assertTrue(res.getBody().asString().contains("\"ErrorCode\":105"), "Error code was not returned");
    }
}

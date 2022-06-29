package com.jalasoft.todoly.icons;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class implements the tests for the Icons endpoint.
 * @author TestNG group: <a href="mailto:saul.fuentes@fundacion-jala.org">Saul Fuentes</a>
 * @version 1.0
 */
public class IconsTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    @BeforeMethod
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void getIconById() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET Icon by Id resquest to the \"/icons/1.json\" endpoint is executed", true);
        Response response = apiManager.get(String.format(environment.getIconsByIdEndPoint(), 1));

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body returned");
        Assert.assertEquals(response.jsonPath().getString("Id"), "1", "Correct response body returned");
        Assert.assertEquals(response.jsonPath().getString("URL"), "http://todo.ly/Images/icons/Home.png", "Correct response body returned");
    }

    @Test
    public void getIconByNonExistentId() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET Icon by Non-existent Id resquest to the \"/icons/100.json\" endpoint is executed", true);
        Response response = apiManager.get(String.format(environment.getIconsByIdEndPoint(), 100));

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorCode").contains("301"), "Correct ErrorCode is returned");
    }

    @Test
    public void getIconByIdWithoutAuthentication() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET Icon by Id resquest to the \"/icons/1.json\" endpoint is executed without authentication", true);
        apiManager.setCredentials("", "");
        Response response = apiManager.get(String.format(environment.getIconsByIdEndPoint(), 1));

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorMessage").contains("Not Authenticated"), "Correct ErrorMessage is returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorCode").contains("102"), "Correct ErrorCode is returned");
    }

    @Test // Bug
    public void getIconByIdWithInvalidId() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET Icon by Id resquest to the \"/icons/abc.json\" endpoint is executed with invalid id", true);
        Response response = apiManager.get(String.format(environment.getIconsByIdEndPoint(), "abc"));

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorMessage").contains("Invalid Id"), "Correct ErrorMessage is returned");
        Assert.assertTrue(response.jsonPath().getString("ErrorCode").contains("301"), "Correct ErrorCode is returned");
    }
}

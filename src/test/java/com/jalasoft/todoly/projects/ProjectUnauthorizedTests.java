package com.jalasoft.todoly.projects;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * The GetAllProjectsTests class implements all the tests for verifying the answers with invalid credentials in the operations of Project's API
 * @author TestNG group: <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 * @version 1.0
 */

public class ProjectUnauthorizedTests {

    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    @Test
    public void getAllProjectsInvalidPassword() {
        Reporter.log("verify that GIVEN the invalid password WHEN the request GET ALL PROJECTS is made then answers with status 200 but error 102",true);
        apiManager.setCredentials(environment.getUserName(), environment.getInvalidPassword());
        Response response = apiManager.get(environment.getProjectsEndpoint());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Not Authenticated\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":102"), "Error Code was not returned");
    }

    @Test
    public void getAllProjectsInvalidUserName() {
        Reporter.log("verify that GIVEN the invalid username WHEN the request GET ALL PROJECTS is made THEN answers with status 200 but error 105",true);
        apiManager.setCredentials(environment.getInvalidUserName(), environment.getPassword());
        Response response = apiManager.get(environment.getProjectsEndpoint());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorMessage"),"Account doesn't exist", "Error Message was not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorCode"),"105", "Error Code was not returned");
    }
}

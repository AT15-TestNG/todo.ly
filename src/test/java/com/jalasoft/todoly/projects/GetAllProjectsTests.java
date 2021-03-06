package com.jalasoft.todoly.projects;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * The GetAllProjectsTests class implements all the tests for verifying the one of the basic Get operations of Project's API
 * Get all the projects of one user.
 * @author TestNG group: <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 * @version 1.0
 */

public class GetAllProjectsTests {

    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private ArrayList<Integer> projectIds = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getGetAllProjectsUser(), environment.getGetAllProjectsPassword());
    }
    @Test
    public void getAllProjects() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET resquest to the \"/projects.json\" endpoint is executed", true);
        Response response = apiManager.get(environment.getProjectsEndpoint());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body returned");
    }
}

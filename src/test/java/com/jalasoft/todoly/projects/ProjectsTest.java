package com.jalasoft.todoly.projects;

import api.APIManager;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectsTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void getProjects() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET resquest to the \"/projects.json\" endpoint is executed", true);
        Response response = apiManager.get(environment.getProjectsEndpoint());

        System.out.println(response.body().asString());
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body returned");

    }
}

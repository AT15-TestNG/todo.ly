package com.jalasoft.todoly.projects;

import api.APIManager;
import entities.NewProject;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectUnauthorizedTests {

    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getInvalidUserName(), environment.getInvalidPassword());
    }

    @Test
    public void getAllProjects() {
        Response response = apiManager.get(environment.getProjectsEndpoint());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorMessage\":\"Not Authenticated\""), "Error Message was not returned");
        Assert.assertTrue(response.getBody().asString().contains("\"ErrorCode\":102"), "Error Code was not returned");
    }

    @Test
    public void createNewProject() {
        NewProject newProject = new NewProject("My Testing Project", 2);
        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, newProject);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorMessage"), "Error Message was not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorCode"), "Error Code was not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorMessage"), "Not Authenticated", "Incorrect Error Message was returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorCode"), "102", "Incorrect Error Code was returned");
    }
}

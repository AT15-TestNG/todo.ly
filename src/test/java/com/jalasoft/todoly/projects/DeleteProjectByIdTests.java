package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Project;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * The DeleteProjectByIdTests class implements all the tests for verifying the basic Delete operations of Project's API
 * @author TestNG group: <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 * @version 1.0
 */

public class DeleteProjectByIdTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();
    private static final int NON_EXISTENT_PROJECT_ID = 9999999;

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ProjectById Test Project", 1));


        if (projects.get(0) == null) {
            Assert.fail("Projects were not created");
        }
    }
    @Test
    public void deleteProjectById() {
        Reporter.log("verify that GIVEN the valid credentials and an existent ID WHEN the request DELETE PROJECT BY ID is made THEN answers with status 200",true);
        Project project = projects.get(0);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Response response = apiManager.delete(projectByIdEndpoint);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertTrue(responseProject.getDeleted(), "Project was not deleted");
    }

    @Test
    public void deleteProjectByIdNonExistentId() {
        Reporter.log("verify that GIVEN the valid credentials and a non existent ID WHEN the request DELETE PROJECT BY ID is made THEN answers with status 200 but error 301",true);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), NON_EXISTENT_PROJECT_ID);
        Response response = apiManager.delete(projectByIdEndpoint);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorMessage"),"Invalid Id", "Error Message was not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorCode"),"301", "Error Code was not returned");
    }
}

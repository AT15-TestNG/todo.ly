package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Project;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * The GetAllProjectsTests class implements all the tests for verifying the one of the basic Get operations of Project's API
 * Get one specific project.
 * @author TestNG group: <a href="mailto:sergio.mendieta@fundacion-jala.org">Sergio Mendieta</a>
 * @version 1.0
 */
public class GetProjectByIdTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ProjectById Test Project", 1));

        if (projects.get(0) == null) {
            Assert.fail("Projects were not created");
        }
    }

    @Test
    public void getProjectById() {
        Reporter.log("verify that GIVEN the valid credentials and an existent ID WHEN the request GET PROJECT BY ID is made THEN answers with status 200",true);
        Project project = projects.get(0);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Response response = apiManager.get(projectByIdEndpoint);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getId(), project.getId(), "Id value is incorrect");
        Assert.assertEquals(responseProject.getContent(), project.getContent(), "Content value is incorrect");
        Assert.assertEquals(responseProject.getIcon(), project.getIcon(), "Icon value is incorrect");
    }

    @AfterClass
    public void teardown() {
        for (Project project : projects) {
            boolean isProjectDeleted = APIProjectMethods.deleteProject(project.getId());
            Assert.assertTrue(isProjectDeleted, "Project was not deleted");
        }
    }

}

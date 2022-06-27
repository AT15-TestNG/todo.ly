package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Project;
import framework.Environment;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateProjectByIdTest {

    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ToTest", 1));
        projects.add(APIProjectMethods.createProject("ProjectById Test Project 2", 2));

        if ((projects.get(0) == null) || (projects.get(1) == null)) {
            Assert.fail("Projects were not created");
        }
    }

    @Test
    public void updateProjectById() {
        Reporter.log("verify that GIVEN the valid credentials, an existent ID and the body with the camp \"Content\" with a new String WHEN the request PUT UPDATE PROJECT BY ID is made THEN answers with status 200",true);
        Project project = projects.get(0);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Content", "toTestUpdated");

        Response response = apiManager.put(projectByIdEndpoint, ContentType.JSON, jsonAsMap);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), jsonAsMap.get("Content"), "Incorrect Content value was set");
    }

    @Test
    public void updateProjectByIdWithEmptyContent() {
        Reporter.log("verify that GIVEN the valid credentials, an existent and the body with the camp \"Content\" empty WHEN the request PUT UPDATE PROJECT BY ID is made THEN answers with status 200 but error 305",true);
        Project project = projects.get(1);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Content", "");

        Response response = apiManager.put(projectByIdEndpoint, ContentType.JSON, jsonAsMap);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorMessage"), "Error Message was not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorCode"), "Error Code was not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorMessage"), "Too Short Project Name", "The error message is: "+response.jsonPath().getString("ErrorMessage")+" instead of "+ "Too Short Project Name");
        Assert.assertEquals(response.jsonPath().getString("ErrorCode"), 304,"The ErrorCode is "+response.jsonPath().getString("ErrorCode")+" instead of 304");
    }

    @AfterClass
    public void teardown() {
        for (Project project : projects) {
            boolean isProjectDeleted = APIProjectMethods.deleteProject(project.getId());
            Assert.assertTrue(isProjectDeleted, "Project was not deleted");
        }
    }
}

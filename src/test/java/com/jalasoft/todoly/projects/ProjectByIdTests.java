package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Project;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectByIdTests {

    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ProjectById Test Project", 1));
        projects.add(APIProjectMethods.createProject("ProjectById Delete Test Project", 2));

        if ((projects.get(0) == null) || (projects.get(1) == null)) {
            Assert.fail("Projects were not created");
        }
    }

    @Test
    public void getProjectById() {
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

    @Test
    public void updateProjectById() {
        Project project = projects.get(0);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Icon", 3);

        Response response = apiManager.put(projectByIdEndpoint, ContentType.JSON, jsonAsMap);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getIcon(), jsonAsMap.get("Icon"), "Incorrect Icon value was set");
    }

    @Test
    public void updateProjectByIdWithJSONFile() {
        Project project = projects.get(0);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        File requestBody = new File("src/test/resources/json/projects/UpdateProject.json");

        Response response = apiManager.put(projectByIdEndpoint, ContentType.JSON, JsonPath.from(requestBody).get());
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getIcon(), JsonPath.from(requestBody).getInt("Icon"), "Incorrect Icon value was set");
    }

    @Test
    public void deleteProjectById() {
        Project project = projects.get(1);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Response response = apiManager.delete(projectByIdEndpoint);
        Project responseProject = response.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertTrue(responseProject.getDeleted(), "Project was not deleted");
    }

    @AfterClass
    public void teardown() {
        for (Project project : projects) {
            boolean isProjectDeleted = APIProjectMethods.deleteProject(project.getId());
            Assert.assertTrue(isProjectDeleted, "Project was not deleted");
        }
    }
}

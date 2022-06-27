package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.NewProject;
import entities.Project;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProjectTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private ArrayList<Integer> projectIds = new ArrayList<>();
    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
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
    @Test
    public void createNewProject() {
        NewProject newProject = new NewProject("My Testing Project", 2);
        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, newProject);
        Project responseProject = response.as(Project.class);
        projectIds.add(responseProject.getId());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), newProject.getContent(), "Incorrect Content value was set");
        Assert.assertEquals(responseProject.getIcon(), newProject.getIcon(), "Incorrect Icon value was set");
    }
    @Test
    public void createNewProjectWithJSONFile() {
        File requestBody = new File("src/test/resources/json/projects/NewProject.json");
        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, JsonPath.from(requestBody).get());
        Project responseProject = response.as(Project.class);
        projectIds.add(responseProject.getId());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), JsonPath.from(requestBody).getString("Content"), "Incorrect Content value was set");
        Assert.assertEquals(responseProject.getIcon(), JsonPath.from(requestBody).getInt("Icon"), "Incorrect Icon value was set");
    }
    @Test
    public void createNewProjectWithMap() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Content", "My Testing Project");
        jsonAsMap.put("Icon", 3);

        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, jsonAsMap);
        Project responseProject = response.as(Project.class);
        projectIds.add(responseProject.getId());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), jsonAsMap.get("Content"), "Incorrect Content value was set");
        Assert.assertEquals(responseProject.getIcon(), jsonAsMap.get("Icon"), "Incorrect Icon value was set");
    }
    @Test
    public void tooShortProjectName() {
        NewProject newProject = new NewProject("", 2);
        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, newProject);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorMessage"), "Error Message was not returned");
        Assert.assertNotNull(response.jsonPath().getString("ErrorCode"), "Error Code was not returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorMessage"), "Too Short Project Name", "Incorrect Error Message was returned");
        Assert.assertEquals(response.jsonPath().getString("ErrorCode"), "305", "Incorrect Error Code was returned");
    }
    @Test
    public void contentLowerCaseBug() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("content", "My Testing Project");
        jsonAsMap.put("Icon", 3);

        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, jsonAsMap);
        Project responseProject = response.as(Project.class);
        projectIds.add(responseProject.getId());

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), jsonAsMap.get("Content"), "Incorrect Content value was set");
        Assert.assertEquals(responseProject.getIcon(), jsonAsMap.get("Icon"), "Incorrect Icon value was set");
    }
    @AfterClass
    public void teardown() {
        projectIds.removeIf(Objects::isNull);
        if (projectIds.size() > 0) {
            for (int projectId : projectIds) {
                boolean isProjectDeleted = APIProjectMethods.deleteProject(projectId);
                Assert.assertTrue(isProjectDeleted, "Project was not deleted");
            }
        }
    }
}

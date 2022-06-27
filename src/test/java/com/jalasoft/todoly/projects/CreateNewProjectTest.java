package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.NewProject;
import entities.Project;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Objects;

public class CreateNewProjectTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private ArrayList<Integer> projectIds = new ArrayList<>();
    private static final String PROJECT_NAME = "ToTest";
    private static final int PROJECT_ICON = 2;
    private static final int OK_RESPONSE_CODE = 200;
    private static final String OK_RESPONSE_ERROR_MESSAGE = "Correct status code is not returned";


    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());
    }

    @Test
    public void createNewProject() {
        NewProject newProject = new NewProject(PROJECT_NAME, PROJECT_ICON);
        Response response = apiManager.post(environment.getProjectsEndpoint(), ContentType.JSON, newProject);
        Project responseProject = response.as(Project.class);
        projectIds.add(responseProject.getId());

        Assert.assertEquals(response.getStatusCode(), OK_RESPONSE_CODE, OK_RESPONSE_ERROR_MESSAGE);
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error Message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getContent(), newProject.getContent(), "Incorrect Content value was set");
        Assert.assertEquals(responseProject.getIcon(), newProject.getIcon(), "Incorrect Icon value was set");
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

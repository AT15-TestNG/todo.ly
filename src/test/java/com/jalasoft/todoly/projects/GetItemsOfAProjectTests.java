package com.jalasoft.todoly.projects;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Item;
import entities.Project;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GetItemsOfAProjectTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ProjectById Test Project", 1));
        items.add(APIProjectMethods.createItem("Item 1", projects.get(0).getId(), false));

        if (projects.get(0) == null) {
            Assert.fail("Projects were not created");
        }
        if (items.get(0) == null) {
            Assert.fail("Item was not created");
        }

    }

    @Test
    public void getItemsOfAProjectById() {
        Reporter.log("verify that GIVEN the valid credentials and an existent ID WHEN the request GET ITEMS OF A PROJECT BY ID is made THEN answers with status 200",true);
        Project project = projects.get(0);
        Item item = items.get(0);
        String itemsProjectByIdEndpoint = String.format(environment.getItemsProjectsEndpoint(), project.getId());
        Response response = apiManager.get(itemsProjectByIdEndpoint);
        Item[] itemsResponse = response.as(Item[].class);
        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), project.getId());
        Response projectResponse = apiManager.get(projectByIdEndpoint);
        Project responseProject = projectResponse.as(Project.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorMessage"), "Error Message was returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorCode"), "Error Code was returned");
        Assert.assertEquals(responseProject.getItemsCount(),1,"not item was found in the project");
        Assert.assertEquals(itemsResponse[0].getContent(), items.get(0).getContent(),"item 1 was not found");
    }
    @AfterClass
    public void teardown() {
        for (Project project : projects) {
            boolean isProjectDeleted = APIProjectMethods.deleteProject(project.getId());
            Assert.assertTrue(isProjectDeleted, "Project was not deleted");
        }
    }
}

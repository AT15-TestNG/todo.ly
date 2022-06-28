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

public class GetDoneItemsOfAProjectTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Project> projects = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(), environment.getPassword());

        projects.add(APIProjectMethods.createProject("ProjectById Test Project", 2));
        items.add(APIProjectMethods.createItem("Done Item 1", null,projects.get(0).getId(), true));

        if (projects.get(0) == null) {
            Assert.fail("Projects were not created");
        }
        if (items.get(0) == null) {
            Assert.fail("Item was not created");
        }
    }

    @Test
    public void getDoneItemsOfAProjectById() {
        Reporter.log("verify that GIVEN the valid credentials and an existent ID WHEN the request GET DONE ITEMS OF A PROJECT BY ID is made THEN answers with status 200",true);
        Project project = projects.get(0);
        Item item = items.get(0);
        String doneItemsProjectByIdEndpoint = String.format(environment.getDoneItemsOfAProjectEndpoint(), project.getId());
        Response response = apiManager.get(doneItemsProjectByIdEndpoint);
        Item[] items = response.as(Item[].class);

        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorMessage"), "Error Message was returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorCode"), "Error Code was returned");
        Assert.assertTrue(items[0].getChecked(),"Done Item 1 is not found");
    }

    @AfterClass
    public void teardown() {
        for (Project project : projects) {
            boolean isProjectDeleted = APIProjectMethods.deleteProject(project.getId());
            Assert.assertTrue(isProjectDeleted, "Project was not deleted");
        }
    }
}

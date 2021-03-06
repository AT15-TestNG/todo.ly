package com.jalasoft.todoly.items;

import api.APIManager;
import api.methods.APIItemMethods;
import entities.Item;
import framework.Environment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GetRootItemByChildIDTest {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Item> items = new ArrayList<>();
    /**
     * The GetRootItemByChildIdTest class implements all the tests for verifying the operation GetRootItemByChildId of Item's API
     * @author TestNG group: <a href="mailto:jimy.tastaca@fundacion-jala.org">Jimy Tastaca</a>
     * @version 1.0
     */
    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(),environment.getPassword());
        items.add(APIItemMethods.createItem("Parent Item Test",null,4000240,false));
        items.add(APIItemMethods.createItem("Child Item Test",items.get(0).getId(),4000240,false));
        if ((items.get(0))==null||(items.get(1))==null) {
            Assert.fail("Items were not created");
        }
    }

    @Test
    public void getRootItemByChildID() {
        Item item = items.get(1);
        String rootItemByIdChildEndpoint = String.format(environment.getRootItemByIdChildEndpoint(), item.getId());
        Response response = apiManager.get(rootItemByIdChildEndpoint);
        Item responseItem = response.as(Item.class);
        Assert.assertEquals(response.getStatusCode(), 200,"Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error code was returned");
        Assert.assertEquals(responseItem.getId(), items.get(0).getId(),"Id value is incorrect");

        Assert.assertEquals(responseItem.getProjectId(), items.get(0).getProjectId(), "ProjectId value is incorrect");
        Assert.assertEquals(responseItem.getChecked(), items.get(0).getChecked(), "Checked value is incorrect");
    }

    @AfterClass
    public void tearDown() {
        for (Item item: items) {
            boolean isItemDeleted = APIItemMethods.deleteItem(item.getId());
            Assert.assertTrue(isItemDeleted,"Item was not deleted");
        }
    }
}

package com.jalasoft.todoly.items;

import api.APIManager;
import api.methods.APIItemMethods;
import entities.Item;
import entities.NewItem;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ErrorMessageTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Item> items = new ArrayList<>();

    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(),environment.getPassword());
        items.add(APIItemMethods.createItem("Parent Item", null ,4000240,false));
        items.add(APIItemMethods.createItem("Child Item", items.get(0).getId(),4000240,false));
        if ((items.get(0))==null||(items.get(1))==null) {
            Assert.fail("Items were not created");
        }
    }

    @Test
    public void tooShortItemName() {
        NewItem newItem = new NewItem("", items.get(0).getId(), 4000240,false);
        Response response = apiManager.post(environment.getItemsEndpoint(), ContentType.JSON, newItem);
        Item responseItem = response.as(Item.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.body().asString().contains("ErrorCode"), "308");
        Assert.assertTrue(response.body().asString().contains("ErrorMessage"), "Too Short Item Name");
    }

    @Test
    public void youDontHaveAccessToThisItem() {
        String rootItemByIdChildEndpoint = String.format(environment.getRootItemByIdChildEndpoint(), 123456);
        Response response = apiManager.get(rootItemByIdChildEndpoint);
        Item responseItem = response.as(Item.class);
        Assert.assertEquals(response.getStatusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertTrue(response.body().asString().contains("ErrorCode"), "309");
        Assert.assertTrue(response.body().asString().contains("ErrorMessage"), "You don't have access to this Item");
    }

    @Test
    public void invalidParentItemId() {
        NewItem newItem = new NewItem("Item Test", 70, 4000240,false);
        Response response = apiManager.post(environment.getItemsEndpoint(), ContentType.JSON, newItem);
        Item responseItem = response.as(Item.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertTrue(response.body().asString().contains("ErrorCode"), "303");
        Assert.assertTrue(response.body().asString().contains("ErrorMessage"), "Invalid Parent Item Id");
    }

    @AfterClass
    public void tearDown() {
        for (Item item: items) {
            boolean isItemDeleted = APIItemMethods.deleteItem(item.getId());
            Assert.assertTrue(isItemDeleted,"Item was not deleted");
        }
    }
}

package com.jalasoft.todoly.items;

import api.APIManager;
import api.methods.APIProjectMethods;
import entities.Item;
import entities.NewItem;
import entities.NewUser;
import entities.User;
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

public class ItemTests {
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    private final ArrayList<Item> items = new ArrayList<>();
    @BeforeClass
    public void setup() {
        apiManager.setCredentials(environment.getUserName(),environment.getPassword());
        items.add(APIProjectMethods.createItem("ItemById Test Item", null ,4000128,false));
        items.add(APIProjectMethods.createItem("ItemById Delete Test Item", null,4000128,false));
        if ((items.get(0))==null||(items.get(1))==null) {
            Assert.fail("Items were not created");
        }
    }
    @Test
    public void getAllItems() {
        Reporter.log("Verify that a 200 OK status code and a correct response body result when a GET request to the", true);
        Response response = apiManager.get(environment.getItemsEndpoint());
        System.out.println(response.jsonPath().get("[1].Content").toString());
        Assert.assertEquals(response.getStatusCode(),200,"Correct status code is no returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorMessage"), "Correct response body is returned");
        Assert.assertFalse(response.getBody().asString().contains("ErrorCode"), "Correct response body is not returned");
    }
    @Test
    public void getItemById() {
        Item item = items.get(0);
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(), item.getId());
        Response response = apiManager.get(itemByIdEndpoint);
        Item responseItem = response.as(Item.class);
        Assert.assertEquals(response.getStatusCode(), 200,"Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error code was returned");
        Assert.assertEquals(responseItem.getId(), item.getId(),"Id value is incorrect");
        Assert.assertEquals(responseItem.getProjectId(), item.getProjectId(), "ProjectId value is incorrect");
        Assert.assertEquals(responseItem.getChecked(), item.getChecked(), "Checked value is incorrect");
    }
    @Test
    public void createNewItem() {
        NewItem newItem = new NewItem("item to test", items.get(0).getId(), 4000128,false);
        Response response = apiManager.post(environment.getItemsEndpoint(), ContentType.JSON, newItem);
        Item responseItem = response.as(Item.class);

        Assert.assertEquals(response.statusCode(), 200, "Correct status code is not returned");
        Assert.assertTrue(response.statusLine().contains("200 OK"), "Correct status code and message are not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorCode"), "Correct response body is not returned");
        Assert.assertFalse(response.body().asString().contains("ErrorMessage"), "Correct response body is not returned");
    }
    @Test
    public void updateItemById() {
        Item item = items.get(0);
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(), item.getId());
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("Checked", true);
        Response response = apiManager.put(itemByIdEndpoint, ContentType.JSON,jsonAsMap);
        Item responseItem = response.as(Item.class);
        Assert.assertEquals(response.getStatusCode(), 200,"Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error code was returned");
        Assert.assertEquals(responseItem.getChecked(),jsonAsMap.get("Checked"), "Incorrect Checked value was set");
    }
    @Test
    public void deleteItemById() {
        Item item = items.get(1);
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(),item.getId());
        Response response = apiManager.delete(itemByIdEndpoint);
        Item responseItem = response.as(Item.class);
        Assert.assertEquals(response.getStatusCode(), 200,"Correct status code is not returned");
        Assert.assertTrue(response.getStatusLine().contains("200 OK"), "Correct status code and message is not returned");
        Assert.assertNull(response.jsonPath().getString("ErrorMessage"), "Error message was returned");
        Assert.assertNull(response.jsonPath().getString("ErrorCode"), "Error code was returned");
        Assert.assertTrue(responseItem.getDeleted(), "Item was not deleted");
    }
    @AfterClass
    public void tearDown() {
        for (Item item: items) {
            boolean isItemDeleted = APIProjectMethods.deleteItem(item.getId());
            Assert.assertTrue(isItemDeleted,"Item was not deleted");
        }
    }
}

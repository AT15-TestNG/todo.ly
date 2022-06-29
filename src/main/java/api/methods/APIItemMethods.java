package api.methods;

import api.APIManager;
import entities.Item;
import entities.NewItem;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.LoggerManager;

import java.io.IOException;

public class APIItemMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();
    /**
     * This method is used to create a new Item.
     * @param content the name of the new item
     * @param parentId an existing item id, in order to create a new child item
     * @param projectId an existing project id, to create new item inside the project
     * @param checked to set the item as done
     * @return new item as class file.
     */
    public static Item createItem(String content, Integer parentId, Integer projectId, Boolean checked) {
        NewItem item = new NewItem(content, parentId, projectId, checked);
        String itemProjectsEndpoint = environment.getCreateItemEndpoint();
        Response response = apiManager.post(itemProjectsEndpoint, ContentType.JSON, item);
        Item responseItemProject = response.as(Item.class);
        if (responseItemProject.getId() != null) {
            return responseItemProject;
        } else {
            log.error("Unable to create a new item");
            return null;
        }
    }
    /**
     * This method is used to delete an existing Item.
     * @param itemId the id of an existing item.
     * @return true if the item was deleted or false if the item was not deleted.
     */
    public static boolean deleteItem(int itemId) {
        log.info("Deleting Item "+ itemId);
        String itemByIdEndpoint = String.format(environment.getItemsByIdEndpoint(),itemId);
        Response response = apiManager.delete(itemByIdEndpoint);
        Item responseItem = response.as(Item.class);
        if (responseItem.getDeleted()!=null) {
            return responseItem.getDeleted();
        } else {
            log.error("Unable to complete request to delete item "+ itemId);
            return false;
        }
    }

}

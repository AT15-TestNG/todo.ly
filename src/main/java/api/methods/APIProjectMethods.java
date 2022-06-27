package api.methods;

import api.APIManager;
import entities.Item;
import entities.NewItem;
import entities.NewProject;
import entities.Project;
import framework.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.LoggerManager;

public class APIProjectMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final Environment environment = Environment.getInstance();
    private static final APIManager apiManager = APIManager.getInstance();

    public static  Project createProject(String content, int icon) {
        NewProject project = new NewProject(content, icon);
        String projectsEndpoint = environment.getProjectsEndpoint();
        Response response = apiManager.post(projectsEndpoint, ContentType.JSON, project);
        Project responseProject = response.as(Project.class);

        if (responseProject.getId() != null) {
            return responseProject;
        } else {
            log.error("Unable to create a new project");
            return null;
        }
    }

    public static boolean deleteProject(int projectId) {
        log.info("Deleting Project " + projectId);
        environment.getProjectByIdEndpoint();

        String projectByIdEndpoint = String.format(environment.getProjectByIdEndpoint(), projectId);
        Response response = apiManager.delete(projectByIdEndpoint);
        Project responseProject = response.as(Project.class);

        if (responseProject.getDeleted() != null) {
            return responseProject.getDeleted();
        } else {
            log.error("Unable to complete request to delete Project " + projectId);
            return false;
        }
    }

    public static Item createItem(String content, Integer projectId, Boolean checked) {
        NewItem item = new NewItem(content, projectId, checked);
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

}

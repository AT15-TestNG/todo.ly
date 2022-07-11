package com.jalasoft.todoly.projects;

import entities.Project;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.XssReader;

import java.io.IOException;

public class ProjectTest {

    @Test
    public void getProject() throws IOException {
        Reporter.log("verify that with valid credentials and an existent ID the request answers with status code 200", true);
        String sheetPath = "src/test/resources/excel/projects.xlsx";
        XssReader.setExcelWorksheet(sheetPath, "Sheet1");
        Project project = Steps.retrieveProject();
        for(int i = 1; i < 5; i++) {
            String action = XssReader.getCellData(i, 1);
            switch (action) {
                case "baseURI" -> Steps.baseURI();
                case "basePath" -> Steps.basePath();
                case "endpoint" -> Steps.endpoint();
                case "request" -> Assert.assertEquals(Steps.request().getStatusCode(), 200, "The request should return status code 200");
                case "retrieveProject" -> Assert.assertNotNull(project, "Project was not retrieved");
            }
        }
    }
}

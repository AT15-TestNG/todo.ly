package com.jalasoft.todoly.projects;

import api.APIManager;
import entities.Project;
import framework.Environment;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;

public class Steps {
    private static final Environment env = Environment.getInstance();
    private static final APIManager api = APIManager.getInstance();
    private static final String projectsByIdEndpoint = String.format(env.getProjectsEndpoint(), 4000240);

    public static void baseURI() {
        RestAssured.baseURI = env.getBaseURL();
    }

    public static void basePath() {
        RestAssured.basePath = env.getBasePath();
    }

    public static void endpoint() {
        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(env.getUserName());
        auth.setPassword(env.getPassword());
        RestAssured.authentication = auth;
    }

    public static Response request() {
        return api.get(projectsByIdEndpoint);
    }

    public static Project retrieveProject() {
        return api.get(projectsByIdEndpoint).as(Project.class);
    }
}


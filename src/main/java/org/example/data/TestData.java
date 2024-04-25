package org.example.data;

public class TestData {

    public static final String URI = "https://reqres.in/api";
    public static final String GET_LIST_USERS = "/users?page=2";
    public static final String GET_SINGLE_USER = "/users/2";
    public static final String POST_CREATE_USER = "/users";
    public static final String GET_SINGLE_USER_NOT_FOUND = "/users/23";
    public static final String GET_LIST_RESOURCE = "/unknown";
    public static final String PUT_UPDATE_USER = "/users/2";
    public static final String DELETE_USER = "/users/2";

    public static final String REQUEST_BODY_CREATE_USER = "{" +
            "\"name\": \"morpheus\"," +
            "\"job\": \"leader\"" +
            "}";

    public static final String REQUEST_BODY_UPDATE_USER = "{" +
            "\"name\": \"morpheus\"," +
            "\"job\": \"zion resident\"" +
            "}";
}

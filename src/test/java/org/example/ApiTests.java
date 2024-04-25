package org.example;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static org.example.data.TestData.*;
import static org.example.data.UserData.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTests {

    @Test(description = "get /users?page=2, statusCode is 200")
    public void getUsers() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(200));
        given()
        .when()
                .get(GET_LIST_USERS)
        .then()
                .log().all()
                .body("per_page", is(6));
    }

    @Test(description = "get /users/2, statusCode is 200", priority = 2)
    public void getUserId() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(200));
        given()
        .when()
                .get(GET_SINGLE_USER)
        .then()
                .log().all()
                .body("data", is(EXPECTED_USER_DATA));
    }

    @Test(description = "post /users, statusCode is 201", priority = 5)
    public void postCreateUser() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(201));
        given()
                .body(REQUEST_BODY_CREATE_USER)
                .post(POST_CREATE_USER)
        .then()
                .log().all()
                .body("id", is(notNullValue()));
    }

    @Test(description = "get /api/users/23, statusCode is 404", priority = 3)
    public void getUserNotFound() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(404));
        given()
        .when()
                .get(GET_SINGLE_USER_NOT_FOUND)
        .then()
                .log().all()
                .body(is("{}"));
    }

    @Test(description = "get /api/unknown, statusCode is 200", priority = 4)
    public void getListResource() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(200));
        given()
        .when()
                .get(GET_LIST_RESOURCE)
        .then()
                .log().all()
                .body("total", is(12));
    }

    @Test(description = "put /api/users/2, statusCOde is 200", priority = 6)
    public void putUpdateUser() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(200));
        given()
                .body(REQUEST_BODY_UPDATE_USER)
                .put(PUT_UPDATE_USER)
        .then()
                .log().all()
                .body("job", is("zion resident"));
    }

    @Test(description = "delete /api/users/2, statusCode is 204", priority = 7)
    public void deleteUser() {

        Specifications.installSpecification(Specifications.requestSpec(URI), Specifications.responseSpec(204));
        given()
        .when()
                .delete(DELETE_USER)
        .then()
                .log().all()
                .body(emptyString());
    }

    @Test(description = "get parse GSON", priority = 1)
    public void testGetUsersParse() {

        RestAssured.baseURI = URI;
        RestAssured.config = config().sslConfig(new SSLConfig().relaxedHTTPSValidation());

        Response res = given()
                .log().all()
                .when()
                .get(GET_SINGLE_USER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Gson gson = new Gson();
        Map<String,Object> user = gson.fromJson(res.asString(), Map.class);
        Assert.assertEquals("Janet", ((Map)user.get("data")).get("first_name"));
    }
}

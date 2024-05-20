package org.example.ApiTests;

import com.google.gson.Gson;
import org.example.BaseTest;
import org.example.pojo.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.example.endpoints.Endpoints.*;

public class ApiTestsGet extends BaseTest {

    @Test(description = "get /users?page=2, statusCode is 200")
    public void getUsers() {

        List<UserData> users = given()
                .queryParam("page", 2)
                .when()
                .get(USERS)
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    @Test(description = "get /users/2, statusCode is 200")
    public void getUserId() {

        String response = given()
                .pathParam("user", 2)
                .when()
                .get(SINGLE_USER)
                .then().log().all()
                .statusCode(200)
                .extract().body().asString();

        Gson gson = new Gson();
        Root root = gson.fromJson(response, Root.class);
        UserData actualUserData = root.getData();
        UserData expectedUserData = new UserData();

        expectedUserData.setId(actualUserData.getId());
        expectedUserData.setFirst_name(actualUserData.getFirst_name());
        expectedUserData.setLast_name(actualUserData.getLast_name());
        expectedUserData.setEmail(actualUserData.getEmail());
        expectedUserData.setAvatar(actualUserData.getAvatar());

        Assert.assertEquals(actualUserData, expectedUserData);
    }

}
